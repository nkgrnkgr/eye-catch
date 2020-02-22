package com.github.nkgrnkgr.eyecatch.api

data class EyeCatch(
    val themeColor: String?,
    val card: String?,
    val site: String?,
    val creator: String?,
    val title: String?,
    val imageUrl: String?,
    val description: String?,
    val url: String?
) {
    data class Builder(
        var themeColor: String? = null,
        var card: String? = null,
        var site: String? = null,
        var creator: String? = null,
        var title: String? = null,
        var imageUrl: String? = null,
        var description: String? = null,
        var url: String? = null
    ) {
        fun themeColor(themeColor: String) = apply { this.themeColor = themeColor }
        fun card(card: String) = apply { this.card = card }
        fun site(site: String) = apply { this.site = site }
        fun creator(creator: String) = apply { this.creator = creator }
        fun title(title: String) = apply { this.title = title }
        fun imageUrl(imageUrl: String) = apply { this.imageUrl = imageUrl }
        fun description(description: String) = apply { this.description = description }
        fun url(url: String) = apply { this.url = url }
        fun build() = EyeCatch(themeColor, card, site, creator, title, imageUrl, description, url)
    }
}

fun createSampleValue(): EyeCatch {
    return EyeCatch(themeColor = "themeColor",
            card = "summary_large_image",
            site = "@nkgrnkgr",
            creator = "@nkgrnkgr",
            title = "Nokogiri - Portfolio",
            imageUrl = "https://raw.githubusercontent.com/nkgrnkgr/portfolio/master/src/images/top.png",
            description = "Nokogiri - Portfolio",
            url = "https://nkgr.app")
}
