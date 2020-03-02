package com.github.nkgrnkgr.eyecatch.api

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("api/eyeCatch")
class EyeCatchController(
    private val eyeCatchService: EyeCatchService
) {

    @GetMapping
    fun getSampleValue(): OutPut {
        return OutPut(200, createSampleValue())
    }

    @PostMapping
    fun getEyeCatchData(@RequestBody input: Input): OutPut {
        val eyeCatch = eyeCatchService.createEyeCatchData(input.url)
        return OutPut(200, eyeCatch)
    }
}
