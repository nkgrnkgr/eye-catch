package com.github.nkgrnkgr.eyecatch.api

import org.jsoup.Jsoup
import org.springframework.stereotype.Service

@Service
class EyeCatchService {

    fun createEyeCatchData(url: String): EyeCatch {
        val doc = Jsoup.connect(url).get()
        val metadataTags = doc.select("meta")
        val eyeCatch = EyeCatch.Builder().title(doc.title()).url(url)

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
                "og:image" -> eyeCatch.imageUrl(metadata.attr("content"))
                "og:description" -> eyeCatch.description(metadata.attr("content"))
                "og:url" -> eyeCatch.url(metadata.attr("content"))
            }
        }

        return eyeCatch.build()
    }
}
