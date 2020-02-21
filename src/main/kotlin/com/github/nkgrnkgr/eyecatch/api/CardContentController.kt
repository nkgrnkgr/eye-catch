package com.github.nkgrnkgr.eyecatch.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("api/site")
class SiteController {

    @GetMapping
    fun getSiteData(): List<String> {
        return mutableListOf<String>("hello", "world")
    }

}