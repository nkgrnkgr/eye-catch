package com.github.nkgrnkgr.eyecatch.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/eyeCatch")
class EyeCatchController {

    @GetMapping
    fun getEyeCatchData(): EyeCatch {
        return createInitialValue()
    }
}