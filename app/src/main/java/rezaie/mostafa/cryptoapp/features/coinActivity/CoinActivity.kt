package rezaie.mostafa.cryptoapp.features.coinActivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import rezaie.mostafa.cryptoapp.R
import rezaie.mostafa.cryptoapp.apiManager.ALL
import rezaie.mostafa.cryptoapp.apiManager.ApiManager
import rezaie.mostafa.cryptoapp.apiManager.BASE_URL_TWITTER
import rezaie.mostafa.cryptoapp.apiManager.HOUR
import rezaie.mostafa.cryptoapp.apiManager.HOURS24
import rezaie.mostafa.cryptoapp.apiManager.MONTH
import rezaie.mostafa.cryptoapp.apiManager.MONTH3
import rezaie.mostafa.cryptoapp.apiManager.WEEK
import rezaie.mostafa.cryptoapp.apiManager.YEAR
import rezaie.mostafa.cryptoapp.apiManager.model.ChartData
import rezaie.mostafa.cryptoapp.apiManager.model.CoinAboutItem
import rezaie.mostafa.cryptoapp.apiManager.model.CoinsData
import rezaie.mostafa.cryptoapp.databinding.ActivityCoinBinding

class CoinActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoinBinding
    lateinit var dataThisCoin: CoinsData.Data
    lateinit var dataThisCoinAbout: CoinAboutItem
    val apiManager = ApiManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fromIntent = intent.getBundleExtra("bundle")!!
        dataThisCoin = fromIntent.getParcelable("bundle1")!!
        if (fromIntent.getParcelable<CoinAboutItem>("bundle2") != null) {
            dataThisCoinAbout = fromIntent.getParcelable("bundle2")!!
        } else {
            dataThisCoinAbout = CoinAboutItem("", "", "", "", "")
        }
        binding.layoutToolbar.toolbar.title = dataThisCoin.coinInfo.fullName
        initUi()
    }

    private fun initUi() {
        initChartUi()
        initStatistics()
        initAbout()
    }

    private fun initChartUi() {
        var period = HOUR
        requestAndShowFData(period)
        binding.layoutChart.radioGroupMain.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.radio_12h -> period = HOUR
                R.id.radio_1day -> period = HOURS24
                R.id.radio_1week -> period = WEEK
                R.id.radio_1moon -> period = MONTH
                R.id.radio_3moon -> period = MONTH3
                R.id.radio_1years -> period = YEAR
                R.id.radio_all -> period = ALL
            }
            requestAndShowFData(period)
        }
        binding.layoutChart.txtChartPrice.text = dataThisCoin.dISPLAY.uSD.pRICE
        binding.layoutChart.txtChartChange1.text = dataThisCoin.dISPLAY.uSD.cHANGE24HOUR
        if (dataThisCoin.coinInfo.fullName == "BUSD") {
            binding.layoutChart.txtChartChange2.text = "0%"
        }
        binding.layoutChart.txtChartChange2.text = dataThisCoin.dISPLAY.uSD.cHANGEPCT24HOUR + "%"
        val taghir = dataThisCoin.rAW.uSD.cHANGEPCT24HOUR
        if (taghir > 0) {
            //binding.root.context
            binding.layoutChart.txtChartChange2.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorGain
                )
            )
        } else if (taghir < 0) {
            binding.layoutChart.txtChartChange2.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorLoss
                )
            )
            binding.layoutChart.txtChartUpdown.text = "▼"
            binding.layoutChart.txtChartUpdown.setTextColor(
                ContextCompat.getColor(this, R.color.colorLoss)
            )

        } else {
            binding.layoutChart.txtChartChange2.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.secondaryTextColor
                )
            )
            binding.layoutChart.txtChartUpdown.text = "▶"
            binding.layoutChart.txtChartUpdown.setTextColor(
                ContextCompat.getColor(this, R.color.secondaryTextColor)
            )
        }
        binding.layoutChart.sparkViewMain.setScrubListener {
            if (it == null)
                binding.layoutChart.txtChartPrice.text = dataThisCoin.dISPLAY.uSD.pRICE
            else
                binding.layoutChart.txtChartPrice.text = "$" +
                        (it as ChartData.Data.Data).close.toString()

        }
    }

    private fun requestAndShowFData(period: String) {
        val symbol = dataThisCoin.coinInfo.name
        apiManager.getChartData(
            symbol = symbol, period = period,
            apiCallback = object :
                ApiManager.ApiCallback<Pair<List<ChartData.Data.Data>, ChartData.Data.Data?>> {
                override fun onSuccess(data: Pair<List<ChartData.Data.Data>, ChartData.Data.Data?>) {
                    val chartAdapter = ChartAdapter(data.first, data.second?.close.toString())
                    binding.layoutChart.sparkViewMain.adapter = chartAdapter
                }

                override fun onFailure(errorMessage: String) {
                    Toast.makeText(this@CoinActivity, errorMessage, Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun initStatistics() {
        binding.moduleStatistics.tvOpenAmount.text = dataThisCoin!!.dISPLAY.uSD.oPEN24HOUR
        binding.moduleStatistics.tvTodaysHighAmount.text = dataThisCoin!!.dISPLAY.uSD.hIGH24HOUR
        binding.moduleStatistics.tvTodayLowAmount.text = dataThisCoin!!.dISPLAY.uSD.lOW24HOUR
        binding.moduleStatistics.tvChangeTodayAmount.text = dataThisCoin!!.dISPLAY.uSD.cHANGE24HOUR
        binding.moduleStatistics.tvAlgorithm.text = dataThisCoin!!.coinInfo.algorithm.toString()
        binding.moduleStatistics.tvTotalVolume.text = dataThisCoin!!.dISPLAY.uSD.tOTALVOLUME24H
        binding.moduleStatistics.tvAvgMarketCapAmount.text = dataThisCoin.dISPLAY.uSD.mKTCAP
        binding.moduleStatistics.tvSupplyNumber.text = dataThisCoin.dISPLAY.uSD.sUPPLY
    }

    private fun initAbout() {
        binding.layoutAbout.txtWebsite.text = dataThisCoinAbout.coinWebsite
        binding.layoutAbout.txtGithub.text = dataThisCoinAbout.coinGithub
        binding.layoutAbout.txtReddit.text = dataThisCoinAbout.coinReddit
        binding.layoutAbout.txtTwitter.text = dataThisCoinAbout.coinTwitter
        binding.layoutAbout.txtAboutCoin.text = dataThisCoinAbout.coinDesc
        if (binding.layoutAbout.txtWebsite.text.equals("")) {
            binding.layoutAbout.txtWebsite.text = "no-data"
            binding.layoutAbout.txtWebsite.isEnabled = false
        }
        if (binding.layoutAbout.txtGithub.text.equals("")) {
            binding.layoutAbout.txtGithub.text = "no-data"
            binding.layoutAbout.txtGithub.isEnabled = false
        }
        if (binding.layoutAbout.txtReddit.text.equals("")) {
            binding.layoutAbout.txtReddit.text = "no-data"
            binding.layoutAbout.txtReddit.isEnabled = false
        }
        if (binding.layoutAbout.txtTwitter.text.equals("")) {
            binding.layoutAbout.txtTwitter.text = "no-data"
            binding.layoutAbout.txtTwitter.isEnabled = false
        }
        if (binding.layoutAbout.txtAboutCoin.text.equals("")) {
            binding.layoutAbout.txtAboutCoin.text = "no-data"
            binding.layoutAbout.txtAboutCoin.isEnabled = false
        }

        binding.layoutAbout.txtWebsite.setOnClickListener {
            openWebSiteDataCoin(dataThisCoinAbout.coinWebsite!!)
        }
        binding.layoutAbout.txtGithub.setOnClickListener {
            openWebSiteDataCoin(dataThisCoinAbout.coinGithub!!)
        }
        binding.layoutAbout.txtReddit.setOnClickListener {
            openWebSiteDataCoin(dataThisCoinAbout.coinReddit!!)
        }
        binding.layoutAbout.txtTwitter.setOnClickListener {
            openWebSiteDataCoin(BASE_URL_TWITTER + "@" + dataThisCoinAbout.coinTwitter!!)
        }
    }

    private fun openWebSiteDataCoin(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}