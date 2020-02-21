package com.example.eyecatch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class SampleController {
    @GetMapping("/samples")
    public List<String> list() {
        return Arrays.asList("a", "b");
    }

    @GetMapping("/samples/{id}")
    public String get(@PathVariable Long id) {
        return "ok";
    }

}