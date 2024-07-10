package rezaie.mostafa.cryptoapp.features.marketActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rezaie.mostafa.cryptoapp.R
import rezaie.mostafa.cryptoapp.apiManager.BASE_URL_IMAGE
import rezaie.mostafa.cryptoapp.apiManager.model.CoinsData
import rezaie.mostafa.cryptoapp.databinding.ItemRecyclerWatchlistBinding

class MarketAdapter(
    private var data: ArrayList<CoinsData.Data>,
    private val recyclerCallback: RecyclerCallback
) :
    RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {
    lateinit var binding: ItemRecyclerWatchlistBinding

    inner class MarketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViews(dataCoin: CoinsData.Data) {
            // جلسه اخر درس میشه
            if (dataCoin.dISPLAY != null && dataCoin.rAW != null) {
                binding.txtCoinName.text = dataCoin.coinInfo.fullName
                //val priceDot = dataCoin.rAW.uSD.pRICE.toString().indexOf(".")
                binding.txtTaghier.text = dataCoin.dISPLAY.uSD.cHANGEPCT24HOUR + "%"
                binding.txtPrice.text = dataCoin.dISPLAY.uSD.pRICE//.toString().substring(0, priceDot + 4)
                val taghir = dataCoin.rAW.uSD.cHANGEPCT24HOUR
                if (taghir > 0) {
                    //binding.root.context
                    binding.txtTaghier.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.colorGain
                        )
                    )
                } else if (taghir < 0) {
                    binding.txtTaghier.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.colorLoss
                        )
                    )

                } else {
                    binding.txtTaghier.setTextColor(ContextCompat.getColor(itemView.context, R.color.secondaryTextColor))
                }
                //val marketCapt = dataCoin.rAW.uSD.mKTCAP / 1000000000
               // val indexDot = marketCapt.toString().indexOf('.')
                binding.txtMarketCap.text =
                        dataCoin.dISPLAY.uSD.mKTCAP
                   // "$" + marketCapt.toString().substring(0, indexDot + 3) + "B"
                Glide.with(itemView).load(BASE_URL_IMAGE + dataCoin.coinInfo.imageUrl)
                    .into(binding.imgItem)
                itemView.setOnClickListener {
                    recyclerCallback.onCoinItemClicked(dataCoin)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        binding =
            ItemRecyclerWatchlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarketViewHolder(binding.root)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        holder.bindViews(data[position])
    }

    interface RecyclerCallback {
        fun onCoinItemClicked(dataCoin: CoinsData.Data)
    }
}