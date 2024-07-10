package rezaie.mostafa.cryptoapp.apiManager.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ChartData(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("HasWarning")
    val hasWarning: Boolean, // false
    @SerializedName("Message")
    val message: String,
    @SerializedName("RateLimit")
    val rateLimit: RateLimit,
    @SerializedName("Response")
    val response: String, // Success
    @SerializedName("Type")
    val type: Int // 100
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("Aggregated")
        val aggregated: Boolean, // false
        @SerializedName("Data")
        val `data`: List<Data>,
        @SerializedName("TimeFrom")
        val timeFrom: Int, // 1710536400
        @SerializedName("TimeTo")
        val timeTo: Int // 1710572400
    ) : Parcelable {
        @Parcelize
        data class Data(
            @SerializedName("close")
            val close: Double, // 68285.75
            @SerializedName("conversionSymbol")
            val conversionSymbol: String,
            @SerializedName("conversionType")
            val conversionType: String, // direct
            @SerializedName("high")
            val high: Double, // 68287.17
            @SerializedName("low")
            val low: Double, // 67819.37
            @SerializedName("open")
            val `open`: Double, // 67861.48
            @SerializedName("time")
            val time: Int, // 1710536400
            @SerializedName("volumefrom")
            val volumefrom: Double, // 1062.59
            @SerializedName("volumeto")
            val volumeto: Double // 72383780.82
        ) : Parcelable
    }

    @Parcelize
    class RateLimit : Parcelable
}