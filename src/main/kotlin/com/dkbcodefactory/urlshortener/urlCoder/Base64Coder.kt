package com.dkbcodefactory.urlshortener.urlCoder

import org.springframework.stereotype.Component
import java.nio.ByteBuffer
import java.util.*

@Component
class Base64Coder: UrlCoder {
    override fun encode(key: Int): String {
        val keyBytes = intToBytes(key)
        return encoder.encodeToString(keyBytes)
    }

    override fun decode(value: String): Int {
        TODO("Not yet implemented")
    }

    private fun intToBytes(key: Int) = ByteBuffer.allocate(Int.SIZE_BYTES).putInt(key).array()

    companion object {
        private val encoder = Base64.getEncoder()
    }
}
