package com.github.nkgrnkgr.eyecatch.api

import java.net.URI
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.stereotype.Component

@Component
class EyeCatchBuilder() {

    fun build(requestUrl: String, doc: Document): EyeCatch {
        val eyeCatchBuilder = createInitialBuilder(requestUrl, doc)
        return buildEyeCatch(eyeCatchBuilder, requestUrl, doc)
    }

    private fun buildEyeCatch(builder: EyeCatch.Builder, requestUrl: String, doc: Document): EyeCatch {
        doc.select("meta").forEach { metadata ->
            when (metadata.attr("name")) {
                "theme-color" -> builder.themeColor(metadata.attr("content"))
                "twitter:card" -> builder.card(metadata.attr("content"))
                "twitter:site" -> builder.site(metadata.attr("content"))
                "twitter:creator" -> builder.creator(metadata.attr("content"))
                "description" -> builder.description(metadata.attr("content"))
            }

            when (metadata.attr("property")) {
                "og:site_name" -> builder.title(metadata.attr("content"))
                "og:title" -> builder.title(metadata.attr("content"))
                "og:image" -> { builder.imageUrl(convertPathRelativeToAbsolute(metadata.attr("content"), requestUrl)) }
                "og:description" -> builder.description(metadata.attr("content"))
                "og:url" -> builder.url(convertPathRelativeToAbsolute(metadata.attr("content"), requestUrl))
            }
        }
        return builder.build()
    }

    private fun createInitialBuilder(requestUrl: String, doc: Document): EyeCatch.Builder {
        val favicon = doc.selectFirst("link[rel='shortcut icon']")
        val icon = doc.selectFirst("link[rel='icon']")
        val appleTouchIcon = doc.selectFirst("link[rel='apple-touch-icon']")
        var href = extractHref(appleTouchIcon, icon, favicon)

        val eyeCatchBuilder = EyeCatch.Builder()
                .title(doc.title())
                .url(requestUrl)

        if (href != null) {
            eyeCatchBuilder.imageUrl(convertPathRelativeToAbsolute(href, requestUrl))
        }

        return eyeCatchBuilder
    }

    private fun convertPathRelativeToAbsolute(path: String, requestUrl: String): String {
        val requestUri = URI(requestUrl)
        val pathUri = URI(path)

        if (!requestUri.isAbsolute) throw Exception("""Request Url '$requestUri' is not absolute. """)
        if (pathUri.isAbsolute) return pathUri.toString()
        if (pathUri.toString() == "") return ""

        return """${requestUri.scheme}://${requestUri.host}$pathUri"""
    }

    private fun extractHref(vararg elements: Element?): String? {
        val list = listOfNotNull(*elements)
        if (list.isEmpty()) return null

        return list.first().attr("href")
    }
}
