package com.dkbcodefactory.urlshortener.dictionary

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

internal class UrlMapTest {

    private val urlMap = UrlMap()

    @Test
    fun getKeyWithNoKeyCreatesANewOne() {
        // given
        val url = "newKey"
        // when
        val value = urlMap.getKey(url)
        // then
        assertNotNull(value)
        assertTrue(value > 0)
    }

    @Test
    fun getKeyWithExistingKeyCreatesANewOne() {
        // given
        val url1 = "newKey"
        val url2 = "newKey"
        val value1 = urlMap.getKey(url1)
        // when
        val value2 = urlMap.getKey(url2)
        // then
        assertNotNull(value2)
        assertEquals(value1, value2)
    }
}
