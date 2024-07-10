package rezaie.mostafa.cryptoapp.apiManager.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

class CoinAboutData : ArrayList<CoinAboutData.CoinAboutDataItem>(){
    @Parcelize
    data class CoinAboutDataItem(
        @SerializedName("currencyName")
        val currencyName: String, // BBC
        @SerializedName("info")
        val info: Info
    ) : Parcelable {
        @Parcelize
        data class Info(
            @SerializedName("desc")
            val desc: String, // TraDove will use Ethereum based tokens, blockchain technologies, and advanced user authentication to solve the fundamental trust problem in B2B and international trade. Authenticated, vetted, and reviewed buyers & sellers will utilize smart contracts to execute trade across a global business-to-business platform, ensuring that payments are successful.LinkedIn is not suitable for business. It does not authenticate its users. Most use public emails. Faking a LinkedIn profile is easy. It is too general and crowded. Most use LinkedIn for personal and professional relations oriented communication. It does not have product/service component.TraDove has developed a proprietary process to authenticate its users in its general network. It is much harder to fake a profile. It is a social network tailored for corporate buying and selling people. It seamlessly connects business people, products/services and companies together to share knowledge, experience and opportunity in a much more relevant way.How BBCoin Will Be UsedWe will let corporate buyers use our services for free. We will let corporate sellers use our basic services for free and use BBCoin to pay for prioritized or value-added utilities, such as notifying sellers as to buyers' searched or expressed needs.
            @SerializedName("forum")
            val forum: String,
            @SerializedName("github")
            val github: String, // https://github.com/Soma-co/soma-community-token
            @SerializedName("reddit")
            val reddit: String, // https://www.reddit.com/r/tradove/
            @SerializedName("twt")
            val twt: String, // B2B_SocialNetwk
            @SerializedName("web")
            val web: String // http://bbcoin.tradove.com
        ) : Parcelable
    }
}