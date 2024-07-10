package rezaie.mostafa.cryptoapp.apiManager

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rezaie.mostafa.cryptoapp.apiManager.model.ChartData
import rezaie.mostafa.cryptoapp.apiManager.model.CoinsData
import rezaie.mostafa.cryptoapp.apiManager.model.NewsData

interface ApiService {
    //@Headers(API_KEY)
    @GET("v2/news/")
    fun getTopNews(@Query("sortOrder") sortOrder: String = "popular"): Call<NewsData>

    @GET("top/totalvolfull")
    fun getTopCoins(
        @Query("tsym") to_symbol: String = "USD",
        @Query("limit") limit_data: Int = 10
    ): Call<CoinsData>

    @GET("v2/{period}")
    fun getChartData(
        @Path("period") period: String,
        @Query("fsym") fromSymbol: String,
        @Query("limit") limit: Int,
        @Query("aggregate") aggregate: Int,
        @Query("tsym") toSymbol: String = "USD"
    ):Call<ChartData>
}