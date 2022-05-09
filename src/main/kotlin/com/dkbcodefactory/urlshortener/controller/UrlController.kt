package com.dkbcodefactory.urlshortener.controller

import com.dkbcodefactory.urlshortener.service.UrlConverter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UrlController(private val converter: UrlConverter) {

    @GetMapping("/shorten")
    fun shorten(@RequestParam url: String): String {
        return converter.shorten(url)
    }

    @GetMapping("/resolve")
    fun resolve(@RequestParam url: String): String {
        // TODO: return 404, 400
        return converter.resolve(url).orEmpty()
    }
}
