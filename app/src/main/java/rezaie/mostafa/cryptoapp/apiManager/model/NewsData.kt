package rezaie.mostafa.cryptoapp.apiManager.model


import com.google.gson.annotations.SerializedName

data class NewsData(
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("HasWarning")
    val hasWarning: Boolean, // false
    @SerializedName("Message")
    val message: String, // News list successfully returned
    @SerializedName("Promoted")
    val promoted: List<Any>,
    @SerializedName("RateLimit")
    val rateLimit: RateLimit,
    @SerializedName("Type")
    val type: Int // 100
) {
    data class Data(
        @SerializedName("body")
        val body: String, // Meme coins like Shiba Inu and Floki Inu saw significant returns, sparking interest in AI token Borroe Finance. #partnercontent
        @SerializedName("categories")
        val categories: String, // SHIB|ICO|SPONSORED
        @SerializedName("downvotes")
        val downvotes: String, // 0
        @SerializedName("guid")
        val guid: String, // https://crypto.news/?p=14257799
        @SerializedName("id")
        val id: String, // 24276789
        @SerializedName("imageurl")
        val imageurl: String, // https://resources.cryptocompare.com/news/73/24276789.jpeg
        @SerializedName("lang")
        val lang: String, // EN
        @SerializedName("published_on")
        val publishedOn: Int, // 1710274190
        @SerializedName("source")
        val source: String, // crypto_news
        @SerializedName("source_info")
        val sourceInfo: SourceInfo,
        @SerializedName("tags")
        val tags: String, // Partner Content|sponsored
        @SerializedName("title")
        val title: String, // Capital flows to Borroe Finance after gains in Shiba Inu and Floki
        @SerializedName("upvotes")
        val upvotes: String, // 0
        @SerializedName("url")
        val url: String // https://crypto.news/capital-flows-to-borroe-finance-after-gains-in-shiba-inu-and-floki/
    ) {
        data class SourceInfo(
            @SerializedName("img")
            val img: String, // https://resources.cryptocompare.com/news/73/default.png
            @SerializedName("lang")
            val lang: String, // EN
            @SerializedName("name")
            val name: String // crypto.news
        )
    }

    class RateLimit
}