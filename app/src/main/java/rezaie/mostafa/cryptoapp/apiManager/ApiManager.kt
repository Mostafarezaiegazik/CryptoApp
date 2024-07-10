package rezaie.mostafa.cryptoapp.apiManager

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rezaie.mostafa.cryptoapp.apiManager.model.ChartData
import rezaie.mostafa.cryptoapp.apiManager.model.CoinsData
import rezaie.mostafa.cryptoapp.apiManager.model.NewsData

class ApiManager {
    private val apiService: ApiService

    init {
        val okHttpClient = OkHttpClient.Builder().addInterceptor {
            val oldRequest = it.request()
            val newRequest = oldRequest.newBuilder()
            newRequest.addHeader("api_key", API_KEY)
            it.proceed(newRequest.build())
        }.build()
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun getNews(apiCallback: ApiCallback<ArrayList<Pair<String, String>>>) {
        apiService.getTopNews().enqueue(object : Callback<NewsData> {
            override fun onResponse(call: Call<NewsData>, response: Response<NewsData>) {
                val data = response.body()!!
                val dataToSend: ArrayList<Pair<String, String>> = arrayListOf()
                data.data.forEach { dataToSend.add(Pair(it.title, it.url)) }
                apiCallback.onSuccess(dataToSend)
            }

            override fun onFailure(call: Call<NewsData>, t: Throwable) {
                apiCallback.onFailure(t.message!!)
            }

        })
    }

    fun getCoinList(apiCallback: ApiCallback<ArrayList<CoinsData.Data>>) {
        apiService.getTopCoins().enqueue(object : Callback<CoinsData> {
            override fun onResponse(call: Call<CoinsData>, response: Response<CoinsData>) {
                val data = response.body()!!
                apiCallback.onSuccess(data.data as ArrayList<CoinsData.Data>)
            }

            override fun onFailure(call: Call<CoinsData>, t: Throwable) {
                apiCallback.onFailure(t.message!!)
            }

        })
    }

    fun getChartData(
        period: String,
        symbol: String,
        apiCallback: ApiCallback<Pair<List<ChartData.Data.Data>, ChartData.Data.Data?>>
    ) {
        var histoPeriod = ""
        var limit = 30
        var aggregate = 1
        when (period) {
            HOUR -> {
                histoPeriod = HISTO_MIINUTE
                limit = 144
                aggregate = 5
            }

            HOURS24 -> {
                histoPeriod = HISTO_MIINUTE
                limit = 144
                aggregate = 10
            }

            WEEK -> {
                histoPeriod = HISTO_HOUR
                limit = 168
            }

            MONTH -> {
                histoPeriod = HISTO_HOUR
                limit = 60
                aggregate = 12
            }

            MONTH3 -> {
                histoPeriod = HISTO_DAY
                limit = 90
            }

            YEAR -> {
                histoPeriod = HISTO_DAY
                limit = 120
                aggregate = 3
            }

            ALL -> {
                histoPeriod = HISTO_DAY
                aggregate = 30
                limit = 2000
            }

        }
        apiService.getChartData(histoPeriod, symbol, limit, aggregate)
            .enqueue(object : Callback<ChartData> {
                override fun onResponse(call: Call<ChartData>, response: Response<ChartData>) {
                    val dataFull = response.body()!!
                    //لیست کندل یا نقاط
                    val data1 = dataFull.data.data
                    // نقطه بیشترین مقدارclose کندل ها
                    val data2 = dataFull.data.data.maxByOrNull {
                        it.close.toFloat()
                    }
                    val returningData = Pair(data1, data2)
                    apiCallback.onSuccess(returningData)
                }

                override fun onFailure(call: Call<ChartData>, t: Throwable) {
                    apiCallback.onFailure(t.message!!)
                    Log.v("123" , t.message!!)
                }

            })
    }

    interface ApiCallback<T> {
        fun onSuccess(data: T)
        fun onFailure(errorMessage: String)
    }
}