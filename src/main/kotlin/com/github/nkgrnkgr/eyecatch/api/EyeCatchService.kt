package com.github.nkgrnkgr.eyecatch.api

import org.jsoup.Jsoup
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class EyeCatchService(
    private val eyeCatchBuilder: EyeCatchBuilder
) {

    val logger = LoggerFactory.getLogger(EyeCatchService::class.java)

    fun createEyeCatchData(requestUrl: String): EyeCatch {
        try {
            val doc = Jsoup.connect(requestUrl).get()
            logger.debug("""SuccessUrl : ${requestUrl}""")
            return eyeCatchBuilder.build(requestUrl, doc)
        } catch (error: Error) {
            throw error;
        }
    }
}
