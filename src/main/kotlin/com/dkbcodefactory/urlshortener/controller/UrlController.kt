package com.dkbcodefactory.urlshortener.controller

import com.dkbcodefactory.urlshortener.service.UrlConverter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UrlController(private val converter: UrlConverter) {

    @GetMapping("/shorten")
    fun shorten(@RequestParam url: String): ResponseEntity<String> {
        val shortUrl = converter.shorten(url)
        return buildResponse(shortUrl)
    }

    @GetMapping("/resolve")
    fun resolve(@RequestParam url: String): ResponseEntity<String> {
        val resolvedUrl = converter.resolve(url)
        return buildResponse(resolvedUrl)
    }

    private fun buildResponse(resolvedUrl: String?): ResponseEntity<String> =
        when {
            resolvedUrl.isNullOrBlank() -> ResponseEntity.notFound().build()
            else -> ResponseEntity.ok(resolvedUrl)
        }
}
