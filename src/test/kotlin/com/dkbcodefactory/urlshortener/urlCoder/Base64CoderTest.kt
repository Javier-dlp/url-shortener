package com.dkbcodefactory.urlshortener.urlCoder

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

internal class Base64CoderTest {

    val coder = Base64Coder()

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
}
