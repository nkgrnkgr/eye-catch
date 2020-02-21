package com.github.nkgrnkgr.eyecatch.api

data class CardContent(val themeColor: String, val card: String, val site: String, val creater: String, val title: String, val imageUrl: String, val description: String, val url: String)

fun createInitialValue(): CardContent {
    return CardContent(themeColor = "themeColro", card = "card", site = "site", creater = "creater", title = "title", imageUrl = "imageUrl", description = "description", url = "url")
}
