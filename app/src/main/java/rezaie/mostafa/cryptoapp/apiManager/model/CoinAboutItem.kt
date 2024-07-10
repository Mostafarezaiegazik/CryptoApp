package rezaie.mostafa.cryptoapp.apiManager.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinAboutItem(
    var coinWebsite: String?,
    var coinGithub: String? ,
    var coinTwitter: String?,
    var coinDesc: String?,
    var coinReddit: String?
): Parcelable