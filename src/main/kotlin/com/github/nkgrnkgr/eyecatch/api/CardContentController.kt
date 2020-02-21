package com.github.nkgrnkgr.eyecatch.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("api/card")
class CardContentController {

    @GetMapping
    fun getSiteData(): CardContent {
        return createInitialValue();
    }

}