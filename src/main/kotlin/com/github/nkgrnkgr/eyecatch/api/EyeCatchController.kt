package com.github.nkgrnkgr.eyecatch.api

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/eyeCatch")
class EyeCatchController(
        private val eyeCatchService: EyeCatchService
) {

    @GetMapping
    fun getInitialValue(): EyeCatch {
        return createInitialValue();
    }

    @PostMapping
    fun getEyeCatchData(@RequestBody input: Input): OutPut {
        val eyeCatch = eyeCatchService.createEyeCatchData(input.url);
        return OutPut(200, eyeCatch)
    }
}
