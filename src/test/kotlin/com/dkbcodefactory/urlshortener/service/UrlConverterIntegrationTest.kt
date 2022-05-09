package com.dkbcodefactory.urlshortener.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


@SpringBootTest
@ContextConfiguration(classes = [UrlConverter ::class]) // we ask Spring to load configuration here
internal class UrlConverterIntegrationTest {

    @Autowired
    private lateinit var converter: UrlConverter

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
