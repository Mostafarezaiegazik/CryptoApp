package rezaie.mostafa.cryptoapp.features.marketActivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import rezaie.mostafa.cryptoapp.apiManager.ApiManager
import rezaie.mostafa.cryptoapp.apiManager.model.CoinAboutData
import rezaie.mostafa.cryptoapp.apiManager.model.CoinAboutItem
import rezaie.mostafa.cryptoapp.apiManager.model.CoinsData
import rezaie.mostafa.cryptoapp.databinding.ActivityMarketBinding
import rezaie.mostafa.cryptoapp.features.coinActivity.CoinActivity

class MarketActivity : AppCompatActivity(), MarketAdapter.RecyclerCallback {
    lateinit var binding: ActivityMarketBinding
    lateinit var dataNews: ArrayList<Pair<String, String>>
    lateinit var aboutDataMap: MutableMap<String, CoinAboutItem>
    val apiManager = ApiManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.layoutToolbar.toolbar.title = "Crypto Market"
        binding.layoutWatchlist.btnShowMore.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.livecoinwatch.com/"))
            startActivity(intent)
        }
        binding.swipeRefreshMain.setOnRefreshListener {
            initUi()
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipeRefreshMain.isRefreshing = false
            }, 1500)
        }
        gerAboutDataFromAssets()
    }

    override fun onResume() {
        super.onResume()
        initUi()
    }

    private fun initUi() {
        getNewsFromApi()
        getTopCoinsFromApi()
    }

    private fun getNewsFromApi() {
        apiManager.getNews(object : ApiManager.ApiCallback<ArrayList<Pair<String, String>>> {
            override fun onSuccess(data: ArrayList<Pair<String, String>>) {
                dataNews = data
                refreshNews()
            }

            override fun onFailure(errorMessage: String) {
                Toast.makeText(this@MarketActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun cleanDataFromServer(data: ArrayList<CoinsData.Data>): ArrayList<CoinsData.Data> {
        val newData = ArrayList<CoinsData.Data>()
        data.forEach { if (it.rAW != null && it.dISPLAY != null && it.coinInfo != null){
            newData.add(it)
        }
        }
        return newData
    }

    fun getTopCoinsFromApi() {
        apiManager.getCoinList(object : ApiManager.ApiCallback<ArrayList<CoinsData.Data>> {
            override fun onSuccess(data: ArrayList<CoinsData.Data>) {
                showDataInRecycler(cleanDataFromServer(data))
            }

            override fun onFailure(errorMessage: String) {
                Log.v("coin", errorMessage)
                Toast.makeText(this@MarketActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun refreshNews() {
        val randomAccess = (0..49).random()
        binding.layoutNews.txtNews.text = dataNews[randomAccess].first
        binding.layoutNews.txtNews.setOnClickListener {
            refreshNews()
        }
        binding.layoutNews.imgNews.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(dataNews[randomAccess].second))
            startActivity(intent)
        }
    }

    fun showDataInRecycler(data: ArrayList<CoinsData.Data>) {
        val adapter = MarketAdapter(data, this)
        binding.layoutWatchlist.recyclerMain.adapter = adapter
        binding.layoutWatchlist.recyclerMain.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCoinItemClicked(dataCoin: CoinsData.Data) {
        val intent = Intent(this, CoinActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("bundle1", dataCoin)
        bundle.putParcelable("bundle2", aboutDataMap[dataCoin.coinInfo.name])
        intent.putExtra("bundle", bundle)
        startActivity(intent)
    }

    fun gerAboutDataFromAssets() {
        val fileInString = applicationContext.assets.open("currencyinfo.json").bufferedReader()
            .use { it.readText() }
        aboutDataMap = mutableMapOf<String, CoinAboutItem>()
        val gson = Gson()
        val dataAboutAll = gson.fromJson(fileInString, CoinAboutData::class.java)
        dataAboutAll.forEach {
            aboutDataMap[it.currencyName] = CoinAboutItem(
                it.info.web,
                it.info.github,
                it.info.twt,
                it.info.desc,
                it.info.reddit
            )
        }
    }
}
