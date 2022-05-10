package com.urlshortener.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import java.net.URI
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class UrlControllerIntegrationTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @LocalServerPort
    var serverPort: Int = 0

    @Test
    fun shortenWorksOK() {
        // given
        val url = "/shorten?url=something"
        // when
        val result = callGetUrl(url)
        // then
        assertEquals(HttpStatus.OK, result.statusCode)
    }

    @Test
    fun shortenNoUrlParamReturns400() {
        // given
        val url = "/shorten"
        // when
        val result = callGetUrl(url)
        // then
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)
    }

    @Test
    fun shortenMalformedUrlReturns400() {
        // given
        val url = "/shorten?url=UnknownHash±!@#\$&*()_+:?"
        // when
        val result = callGetUrl(url)
        // then
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)
    }

    @Test
    fun resolveShortenedUrlReturnsTheSameValue() {
        // given
        val urlToShort = "https://www.some-url.com"
        val shortenUrl = "/shorten?url=$urlToShort"
        val shortUrl = callGetUrl(shortenUrl).body!!
        val url = "/resolve?url=$shortUrl"
        // when
        val result = callGetUrl(url)
        // then
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(urlToShort, result.body)
    }

    @Test
    fun resolveUnknownUrlReturns404() {
        // given
        val url = "/resolve?url=UnknownHash"
        // when
        val result = callGetUrl(url)
        // then
        assertEquals(HttpStatus.NOT_FOUND, result.statusCode)
    }

    @Test
    fun resolveNoUrlParamReturns400() {
        // given
        val url = "/resolve"
        // when
        val result = callGetUrl(url)
        // then
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)
    }

    @Test
    fun resolveMalformedUrlReturns400() {
        // given
        val url = "/resolve?url=UnknownHash±!@#\$&*()_+:?"
        // when
        val result = callGetUrl(url)
        // then
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)
    }

    private fun callGetUrl(url: String) = restTemplate.exchange(
        URI(applicationUrl() + url),
        HttpMethod.GET,
        HttpEntity(""),
        String::class.java
    )

    private fun applicationUrl() = "http://localhost:$serverPort"
}
