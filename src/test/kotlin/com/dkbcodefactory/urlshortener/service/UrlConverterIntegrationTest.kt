package com.dkbcodefactory.urlshortener.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


@SpringBootTest
internal class UrlConverterIntegrationTest {

    @Autowired
    private lateinit var converter: UrlConverter

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
        println("++++++++++ ${converter.customDomain} ++++++++++")
        // then
        assertNotNull(converter.customDomain)
        assertTrue(shortUrl.startsWith(converter.customDomain))
    }
}
