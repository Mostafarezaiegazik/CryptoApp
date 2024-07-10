package rezaie.mostafa.cryptoapp.apiManager.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class CoinsData(
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("HasWarning")
    val hasWarning: Boolean, // false
    @SerializedName("Message")
    val message: String, // Success
    @SerializedName("MetaData")
    val metaData: MetaData,
    @SerializedName("RateLimit")
    val rateLimit: RateLimit,
    @SerializedName("SponsoredData")
    val sponsoredData: List<Any>,
    @SerializedName("Type")
    val type: Int // 100
) {
    @Parcelize
    data class Data(
        @SerializedName("CoinInfo")
        val coinInfo: CoinInfo,
        @SerializedName("DISPLAY")
        val dISPLAY: DISPLAY,
        @SerializedName("RAW")
        val rAW: RAW
    ) : Parcelable {
        @Parcelize
        data class CoinInfo(
            @SerializedName("Algorithm")
            val algorithm: String, // SHA-256
            @SerializedName("AssetLaunchDate")
            val assetLaunchDate: String, // 2009-01-03
            @SerializedName("BlockNumber")
            val blockNumber: Int, // 834400
            @SerializedName("BlockReward")
            val blockReward: Double, // 6.25
            @SerializedName("DocumentType")
            val documentType: String, // Webpagecoinp
            @SerializedName("FullName")
            val fullName: String, // Bitcoin
            @SerializedName("Id")
            val id: String, // 1182
            @SerializedName("ImageUrl")
            val imageUrl: String, // /media/37746251/btc.png
            @SerializedName("Internal")
            val `internal`: String, // BTC
            @SerializedName("MaxSupply")
            val maxSupply: Double, // 20999999.9769
            @SerializedName("Name")
            val name: String, // BTC
            @SerializedName("ProofType")
            val proofType: String, // PoW
            @SerializedName("Rating")
            val rating: Rating,
            @SerializedName("Type")
            val type: Int, // 1
            @SerializedName("Url")
            val url: String // /coins/btc/overview
        ) : Parcelable {
            @Parcelize
            data class Rating(
                @SerializedName("Weiss")
                val weiss: Weiss
            ) : Parcelable {
                @Parcelize
                data class Weiss(
                    @SerializedName("MarketPerformanceRating")
                    val marketPerformanceRating: String, // D
                    @SerializedName("Rating")
                    val rating: String, // B+
                    @SerializedName("TechnologyAdoptionRating")
                    val technologyAdoptionRating: String // A-
                ) : Parcelable
            }
        }

        @Parcelize
        data class DISPLAY(
            @SerializedName("USD")
            val uSD: USD
        ) : Parcelable {
            @Parcelize
            data class USD(
                @SerializedName("CHANGE24HOUR")
                val cHANGE24HOUR: String, // $ -625.53
                @SerializedName("CHANGEDAY")
                val cHANGEDAY: String, // $ -709.37
                @SerializedName("CHANGEHOUR")
                val cHANGEHOUR: String, // $ 39.39
                @SerializedName("CHANGEPCT24HOUR")
                val cHANGEPCT24HOUR: String, // -0.87
                @SerializedName("CHANGEPCTDAY")
                val cHANGEPCTDAY: String, // -0.98
                @SerializedName("CHANGEPCTHOUR")
                val cHANGEPCTHOUR: String, // 0.06
                @SerializedName("CONVERSIONLASTUPDATE")
                val cONVERSIONLASTUPDATE: String, // Just now
                @SerializedName("CONVERSIONTYPE")
                val cONVERSIONTYPE: String, // direct
                @SerializedName("FROMSYMBOL")
                val fROMSYMBOL: String, // Ƀ
                @SerializedName("HIGH24HOUR")
                val hIGH24HOUR: String, // $ 72,997.0
                @SerializedName("HIGHDAY")
                val hIGHDAY: String, // $ 72,997.0
                @SerializedName("HIGHHOUR")
                val hIGHHOUR: String, // $ 71,638.8
                @SerializedName("IMAGEURL")
                val iMAGEURL: String, // /media/37746251/btc.png
                @SerializedName("LASTMARKET")
                val lASTMARKET: String, // CCCAGG
                @SerializedName("LASTTRADEID")
                val lASTTRADEID: String, // 252383681
                @SerializedName("LASTUPDATE")
                val lASTUPDATE: String, // Just now
                @SerializedName("LASTVOLUME")
                val lASTVOLUME: String, // Ƀ 0.003097
                @SerializedName("LASTVOLUMETO")
                val lASTVOLUMETO: String, // $ 221.21
                @SerializedName("LOW24HOUR")
                val lOW24HOUR: String, // $ 68,636.4
                @SerializedName("LOWDAY")
                val lOWDAY: String, // $ 68,636.4
                @SerializedName("LOWHOUR")
                val lOWHOUR: String, // $ 71,156.3
                @SerializedName("MARKET")
                val mARKET: String, // CryptoCompare Index
                @SerializedName("MKTCAP")
                val mKTCAP: String, // $ 1,402.92 B
                @SerializedName("MKTCAPPENALTY")
                val mKTCAPPENALTY: String, // 0 %
                @SerializedName("OPEN24HOUR")
                val oPEN24HOUR: String, // $ 72,012.0
                @SerializedName("OPENDAY")
                val oPENDAY: String, // $ 72,095.9
                @SerializedName("OPENHOUR")
                val oPENHOUR: String, // $ 71,347.1
                @SerializedName("PRICE")
                val pRICE: String, // $ 71,386.5
                @SerializedName("SUPPLY")
                val sUPPLY: String, // Ƀ 19,652,500.0
                @SerializedName("TOPTIERVOLUME24HOUR")
                val tOPTIERVOLUME24HOUR: String, // Ƀ 57,720.7
                @SerializedName("TOPTIERVOLUME24HOURTO")
                val tOPTIERVOLUME24HOURTO: String, // $ 4,120,592,430.0
                @SerializedName("TOSYMBOL")
                val tOSYMBOL: String, // $
                @SerializedName("TOTALTOPTIERVOLUME24H")
                val tOTALTOPTIERVOLUME24H: String, // Ƀ 384.57 K
                @SerializedName("TOTALTOPTIERVOLUME24HTO")
                val tOTALTOPTIERVOLUME24HTO: String, // $ 27.45 B
                @SerializedName("TOTALVOLUME24H")
                val tOTALVOLUME24H: String, // Ƀ 610.08 K
                @SerializedName("TOTALVOLUME24HTO")
                val tOTALVOLUME24HTO: String, // $ 43.55 B
                @SerializedName("VOLUME24HOUR")
                val vOLUME24HOUR: String, // Ƀ 57,720.7
                @SerializedName("VOLUME24HOURTO")
                val vOLUME24HOURTO: String, // $ 4,120,592,430.0
                @SerializedName("VOLUMEDAY")
                val vOLUMEDAY: String, // Ƀ 52,811.9
                @SerializedName("VOLUMEDAYTO")
                val vOLUMEDAYTO: String, // $ 3,765,426,029.9
                @SerializedName("VOLUMEHOUR")
                val vOLUMEHOUR: String, // Ƀ 5,741.74
                @SerializedName("VOLUMEHOURTO")
                val vOLUMEHOURTO: String // $ 410,550,622.8
            ) : Parcelable
        }

        @Parcelize
        data class RAW(
            @SerializedName("USD")
            val uSD: USD
        ) : Parcelable {
            @Parcelize
            data class USD(
                @SerializedName("CHANGE24HOUR")
                val cHANGE24HOUR: Double, // -625.5331814999081
                @SerializedName("CHANGEDAY")
                val cHANGEDAY: Double, // -709.3729691713961
                @SerializedName("CHANGEHOUR")
                val cHANGEHOUR: Double, // 39.392645796600846
                @SerializedName("CHANGEPCT24HOUR")
                val cHANGEPCT24HOUR: Double, // -0.8686510464635631
                @SerializedName("CHANGEPCTDAY")
                val cHANGEPCTDAY: Double, // -0.9839302186305443
                @SerializedName("CHANGEPCTHOUR")
                val cHANGEPCTHOUR: Double, // 0.055212682764857524
                @SerializedName("CONVERSIONLASTUPDATE")
                val cONVERSIONLASTUPDATE: Int, // 1710273259
                @SerializedName("CONVERSIONTYPE")
                val cONVERSIONTYPE: String, // direct
                @SerializedName("FLAGS")
                val fLAGS: String, // 1
                @SerializedName("FROMSYMBOL")
                val fROMSYMBOL: String, // BTC
                @SerializedName("HIGH24HOUR")
                val hIGH24HOUR: Double, // 72996.9883689505
                @SerializedName("HIGHDAY")
                val hIGHDAY: Double, // 72996.9883689505
                @SerializedName("HIGHHOUR")
                val hIGHHOUR: Double, // 71638.7828656655
                @SerializedName("IMAGEURL")
                val iMAGEURL: String, // /media/37746251/btc.png
                @SerializedName("LASTMARKET")
                val lASTMARKET: String, // CCCAGG
                @SerializedName("LASTTRADEID")
                val lASTTRADEID: String, // 252383681
                @SerializedName("LASTUPDATE")
                val lASTUPDATE: Int, // 1710273259
                @SerializedName("LASTVOLUME")
                val lASTVOLUME: Double, // 0.00309677
                @SerializedName("LASTVOLUMETO")
                val lASTVOLUMETO: Double, // 221.20537787
                @SerializedName("LOW24HOUR")
                val lOW24HOUR: Double, // 68636.4292231082
                @SerializedName("LOWDAY")
                val lOWDAY: Double, // 68636.4292231082
                @SerializedName("LOWHOUR")
                val lOWHOUR: Double, // 71156.3446892486
                @SerializedName("MARKET")
                val mARKET: String, // CCCAGG
                @SerializedName("MEDIAN")
                val mEDIAN: Double, // 71386.4886823507
                @SerializedName("MKTCAP")
                val mKTCAP: Double, // 1402922968829.897
                @SerializedName("MKTCAPPENALTY")
                val mKTCAPPENALTY: Int, // 0
                @SerializedName("OPEN24HOUR")
                val oPEN24HOUR: Double, // 72012.0218638506
                @SerializedName("OPENDAY")
                val oPENDAY: Double, // 72095.8616515221
                @SerializedName("OPENHOUR")
                val oPENHOUR: Double, // 71347.0960365541
                @SerializedName("PRICE")
                val pRICE: Double, // 71386.4886823507
                @SerializedName("SUPPLY")
                val sUPPLY: Double, // 120093483.75647336
                @SerializedName("TOPTIERVOLUME24HOUR")
                val tOPTIERVOLUME24HOUR: Double, // 57720.66001957
                @SerializedName("TOPTIERVOLUME24HOURTO")
                val tOPTIERVOLUME24HOURTO: Float, // 4120592429.98669
                @SerializedName("TOSYMBOL")
                val tOSYMBOL: String, // USD
                @SerializedName("TOTALTOPTIERVOLUME24H")
                val tOTALTOPTIERVOLUME24H: Double, // 384565.701457417
                @SerializedName("TOTALTOPTIERVOLUME24HTO")
                val tOTALTOPTIERVOLUME24HTO: Double, // 27452912281.471996
                @SerializedName("TOTALVOLUME24H")
                val tOTALVOLUME24H: Double, // 610083.532880978
                @SerializedName("TOTALVOLUME24HTO")
                val tOTALVOLUME24HTO: Double, // 43551838402.058304
                @SerializedName("TYPE")
                val tYPE: String, // 5
                @SerializedName("VOLUME24HOUR")
                val vOLUME24HOUR: Double, // 57720.66001957
                @SerializedName("VOLUME24HOURTO")
                val vOLUME24HOURTO: Double, // 4120592429.98669
                @SerializedName("VOLUMEDAY")
                val vOLUMEDAY: Double, // 52811.87991547
                @SerializedName("VOLUMEDAYTO")
                val vOLUMEDAYTO: Double, // 3765426029.94301
                @SerializedName("VOLUMEHOUR")
                val vOLUMEHOUR: Double, // 5741.73699475
                @SerializedName("VOLUMEHOURTO")
                val vOLUMEHOURTO: Double // 410550622.808958
            ) : Parcelable
        }
    }
    data class MetaData(
        @SerializedName("Count")
        val count: Int // 2067
    )

    class RateLimit
}