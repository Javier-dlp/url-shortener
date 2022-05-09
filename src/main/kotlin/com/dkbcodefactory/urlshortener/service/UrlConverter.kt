package com.dkbcodefactory.urlshortener.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.ByteBuffer
import java.util.*

@Service
class UrlConverter {

    @Value("\${com.dkbcodefactory.domainName}")
    lateinit var customDomain: String

    var items = TreeMap<Int, String>()

    fun shorten(url: String): String {
        LOG.debug("Shortening url $url")
        val key: Int = getKey(url)
        val encodedKey: String = encodeKey(key)
        LOG.debug("Url $url got shortened to $customDomain/$encodedKey")
        return "$customDomain/$encodedKey"
    }

    private fun getKey(url: String): Int {
        val key: Int
        val filterValues = items.filterValues { it == url }
        if (filterValues.isNotEmpty()) {
            key = filterValues.keys.first()
        } else {
            key = items.size + 1
            items[key] = url
        }
        return key
    }

    private fun encodeKey(key: Int): String {
        val keyBytes = intToBytes(key)
        return encoder.encodeToString(keyBytes)
    }

    private fun intToBytes(key: Int) = ByteBuffer.allocate(Int.SIZE_BYTES).putInt(key).array()

    companion object {
        private val LOG = LoggerFactory.getLogger(UrlConverter::class.java)
        private val encoder = Base64.getEncoder()
    }
}
