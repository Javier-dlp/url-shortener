package com.dkbcodefactory.urlshortener.service

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class UrlConverterTest {

    val converter = UrlConverter()

    @BeforeTest
    fun setUp() {
//        properties.domainName = domain
        converter.customDomain = domain
    }

    @Test
    fun shortenedUrlIsShorterThanLongUrl() {
        // given
        val url = "https://wwww.a-long-url-one-might-say-but-is-up-to-discussion.com/path-with-name-yet-to-be-defined"
        // when
        val shortUrl = converter.shorten(url)
        // then
        assertTrue(shortUrl.length < url.length)
    }

    @Test
    fun sameUrlsShortenedHaveTheSameValue() {
        //given
        val url1 = "https://www.example-url.com/example-path?parameter=empty"
        val url2 = "https://www.example-url.com/example-path?parameter=empty"
        // when
        val shortUrl1 = converter.shorten(url1)
        val shortUrl2 = converter.shorten(url2)
        //then
        assertEquals(shortUrl1, shortUrl2)
    }

    @Test
    fun shortenedUrlStartsWithOurDomain() {
        // given
        val url = "https://some-url.com"
        // when
        val shortUrl = converter.shorten(url)
        // then
        assertTrue(shortUrl.startsWith(domain))
    }

    companion object {
        private const val domain = "https://MyDomain.com"
    }
}
