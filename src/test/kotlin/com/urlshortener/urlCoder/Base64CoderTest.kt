package com.urlshortener.urlCoder

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

internal class Base64CoderTest {

    private val coder = Base64Coder()

    @Test
    fun encodedKeyWorksOK() {
        // given
        val key = 123
        // when
        val encoded = coder.encode(key)
        // then
        assertNotNull(encoded)
        assertTrue(encoded.isNotEmpty())
    }

    @Test
    fun twoEncodedKeysReturnTheSameValue() {
        // given
        val key1 = 123
        val key2 = 123
        // when
        val encoded1 = coder.encode(key1)
        val encoded2 = coder.encode(key2)
        // then
        assertNotNull(encoded1)
        assertNotNull(encoded2)
        assertTrue(encoded1.isNotEmpty())
        assertTrue(encoded2.isNotEmpty())
        assertEquals(encoded1, encoded2)
    }

    @Test
    fun decodedKeyReturnsValueForEncodedKey() {
        // given
        val key = 123
        val encoded = coder.encode(key)
        // when
        val decoded = coder.decode(encoded)
        // then
        assertEquals(key, decoded)
    }

    @Test
    fun decodedKeyReturnsNullWhenIsUnknown() {
        // given
        val encoded = "someValue"
        // when
        val decoded = coder.decode(encoded)
        // then
        assertNull(decoded)
    }

    @Test
    fun twoDecodedKeysAreIdentical() {
        // given
        val key1 = 123
        val key2 = 123
        val encoded1 = coder.encode(key1)
        val encoded2 = coder.encode(key2)
        // when
        val decoded1 = coder.decode(encoded1)
        val decoded2 = coder.decode(encoded2)
        // then
        assertEquals(decoded1, decoded2)
    }
}
