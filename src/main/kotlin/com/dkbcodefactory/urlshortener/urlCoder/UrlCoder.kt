package com.dkbcodefactory.urlshortener.urlCoder

interface UrlCoder {
    fun encode(key: Int): String
    fun decode(value: String): Int
}
