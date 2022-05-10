package com.urlshortener.dictionary

interface UrlDictionary {
    fun getKey(url: String): Int
    fun getUrl(key: Int): String?
}
