package com.github.nkgrnkgr.eyecatch.api

data class EyeCatch(val themeColor: String, val card: String, val site: String, val creator: String, val title: String, val imageUrl: String, val description: String, val url: String)

fun createInitialValue(): EyeCatch {
    return EyeCatch(themeColor = "themeColor", card = "card", site = "site", creator = "creator", title = "title", imageUrl = "imageUrl", description = "description", url = "url")
}
