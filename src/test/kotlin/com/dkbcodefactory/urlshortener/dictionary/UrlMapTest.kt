package com.dkbcodefactory.urlshortener.dictionary

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

internal class UrlMapTest {

    private val urlMap = UrlMap()

    @Test
    fun getKeyWithNoKeyCreatesANewOne() {
        // given
        val url = "newKey"
        // when
        val key = urlMap.getKey(url)
        // then
        assertNotNull(key)
        assertTrue(key > 0)
    }

    @Test
    fun getKeyWithExistingKeyCreatesANewOne() {
        // given
        val url1 = "newKey"
        val url2 = "newKey"
        val key1 = urlMap.getKey(url1)
        // when
        val key2 = urlMap.getKey(url2)
        // then
        assertNotNull(key2)
        assertEquals(key1, key2)
    }

    @Test
    fun getUrlWithUnknownKeyReturnsNull() {
        // given
        val key = Integer.MAX_VALUE
        // when
        val value = urlMap.getUrl(key)
        // then
        assertNull(value)
    }

    @Test
    fun getUrlWithKnownKeyReturnsNull() {
        // given
        val url = "newKey"
        val key = urlMap.getKey(url)
        // when
        val value = urlMap.getUrl(key)
        // then
        assertNotNull(value)
        assertTrue(value.isNotEmpty())
    }
}
