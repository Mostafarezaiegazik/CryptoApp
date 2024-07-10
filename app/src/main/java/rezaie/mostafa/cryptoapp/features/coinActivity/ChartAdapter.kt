package rezaie.mostafa.cryptoapp.features.coinActivity

import com.robinhood.spark.SparkAdapter
import rezaie.mostafa.cryptoapp.apiManager.model.ChartData

class ChartAdapter(
    private val historicalData: List<ChartData.Data.Data>,
    private val baseLine: String?
) : SparkAdapter() {
    override fun getCount(): Int {
        return historicalData.size
    }

    override fun getItem(index: Int): ChartData.Data.Data {
        return historicalData[index]
    }

    override fun getY(index: Int): Float {
       return historicalData[index].close.toFloat()
    }

    override fun hasBaseLine(): Boolean {
        return true
    }

    override fun getBaseLine(): Float {
        //اگر نقطه پایانی نال نباشد نمایش میدهد در غیر این صورت مقدار پیش فرض را شامل میشود
        return baseLine?.toFloat() ?: super.getBaseLine()
    }
}