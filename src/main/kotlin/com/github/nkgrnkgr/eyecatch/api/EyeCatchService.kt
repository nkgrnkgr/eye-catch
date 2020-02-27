package com.github.nkgrnkgr.eyecatch.api

import java.net.URI
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service

@Service
class EyeCatchService {

    fun createEyeCatchData(requestUrl: String): EyeCatch {
        val doc = Jsoup.connect(requestUrl).get()

        val favicon = doc.selectFirst("link[rel='shortcut icon']")
        val icon = doc.selectFirst("link[rel='icon']")
        val appleTouchIcon = doc.selectFirst("link[rel='apple-touch-icon']")
        var href = extractHref(appleTouchIcon, icon, favicon)

        val eyeCatch = EyeCatch.Builder()
                .title(doc.title())
                .url(requestUrl)

        if (href != null) {
            eyeCatch.imageUrl(convertPathRelativeToAbsolute(href, requestUrl))
        }

        val metadataTags = doc.select("meta")
        metadataTags.forEach { metadata ->
            when (metadata.attr("name")) {
                "theme-color" -> eyeCatch.themeColor(metadata.attr("content"))
                "twitter:card" -> eyeCatch.card(metadata.attr("content"))
                "twitter:site" -> eyeCatch.site(metadata.attr("content"))
                "twitter:creator" -> eyeCatch.creator(metadata.attr("content"))
                "description" -> eyeCatch.description(metadata.attr("content"))
            }

            when (metadata.attr("property")) {
                "og:site_name" -> eyeCatch.title(metadata.attr("content"))
                "og:title" -> eyeCatch.title(metadata.attr("content"))
                "og:image" -> { eyeCatch.imageUrl(convertPathRelativeToAbsolute(metadata.attr("content"), requestUrl)) }
                "og:description" -> eyeCatch.description(metadata.attr("content"))
                "og:url" -> eyeCatch.url(convertPathRelativeToAbsolute(metadata.attr("content"), requestUrl))
            }
        }

        return eyeCatch.build()
    }
}

fun convertPathRelativeToAbsolute(path: String, requestUrl: String): String {

    val requestUri = URI(requestUrl)
    val pathUri = URI(path)

    if (!requestUri.isAbsolute) throw Exception("""Request Url '$requestUri' is not absolute. """)
    if (pathUri.isAbsolute) return pathUri.toString()
    if (pathUri.toString() == "") return ""

    return """${requestUri.scheme}://${requestUri.host}$pathUri"""
}

fun extractHref(vararg elements: Element?): String? {
    val list = listOfNotNull(*elements)
    if (list.isEmpty()) return null

    return list.first().attr("href")
}
