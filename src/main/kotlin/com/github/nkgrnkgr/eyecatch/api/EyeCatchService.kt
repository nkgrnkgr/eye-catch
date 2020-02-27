package com.github.nkgrnkgr.eyecatch.api

import java.net.URI
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service

@Service
class EyeCatchService {

    fun createEyeCatchData(requestUrl: String): EyeCatch {
        val doc = Jsoup.connect(requestUrl).get()
        val metadataTags = doc.select("meta")
        val favicon = doc.selectFirst("link[rel='shortcut icon']")
        val icon = doc.selectFirst("link[rel='icon']")

        val eyeCatch = EyeCatch.Builder()
                .title(doc.title())
                .url(requestUrl)
                .imageUrl(convertPathRelativeToAbsolute(extractHref(icon, favicon), requestUrl))

        metadataTags.forEach { metadata ->
            when (metadata.attr("name")) {
                "theme-color" -> eyeCatch.themeColor(metadata.attr("content"))
                "twitter:card" -> eyeCatch.card(metadata.attr("content"))
                "twitter:site" -> eyeCatch.site(metadata.attr("content"))
                "twitter:creator" -> eyeCatch.creator(metadata.attr("content"))
                "description" -> eyeCatch.description(metadata.attr("content"))
            }

            when (metadata.attr("property")) {
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

    return """${requestUri.scheme}://${requestUri.host}$pathUri"""
}

fun extractHref(vararg elements: Element?): String {
    val list = listOfNotNull(*elements)
    if (list.isEmpty()) return ""

    return list.first().attr("href")
}
