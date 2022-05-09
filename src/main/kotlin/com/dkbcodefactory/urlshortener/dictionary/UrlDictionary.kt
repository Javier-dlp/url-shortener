package com.dkbcodefactory.urlshortener.dictionary

interface UrlDictionary {
    fun getKey(url: String): Int
    fun getUrl(key: Int): String?
}
