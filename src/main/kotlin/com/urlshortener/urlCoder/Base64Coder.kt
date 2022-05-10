package com.urlshortener.urlCoder

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.nio.ByteBuffer
import java.util.*

@Component
class Base64Coder: UrlCoder {
    override fun encode(key: Int): String {
        val keyBytes = intToBytes(key)
        return encoder.encodeToString(keyBytes)
    }

    override fun decode(value: String): Int? {
        return try {
            val decoded = decoder.decode(value)
            bytesToInt(decoded)
        } catch (exception: java.lang.IllegalArgumentException) {
            log.warn("Attempted to decode unknown value $value", exception)
            null
        }
    }

    private fun intToBytes(key: Int) = ByteBuffer.allocate(Int.SIZE_BYTES).putInt(key).array()

    private fun bytesToInt(decoded: ByteArray): Int = ByteBuffer.wrap(decoded).int

    companion object {
        private val log = LoggerFactory.getLogger(Base64Coder::class.java)
        private val encoder = Base64.getEncoder()
        private val decoder = Base64.getDecoder()
    }
}
