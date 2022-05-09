package com.dkbcodefactory.urlshortener.service

import com.dkbcodefactory.urlshortener.dictionary.UrlDictionary
import com.dkbcodefactory.urlshortener.urlCoder.UrlCoder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class UrlConverter(
    private val dictionary: UrlDictionary,
    private val coder: UrlCoder
) {

    @Value("\${com.dkbcodefactory.domainName}")
    lateinit var customDomain: String

    fun shorten(url: String): String {
        LOG.debug("Shortening url $url")
        val key: Int = dictionary.getKey(url)
        val encodedKey: String = coder.encode(key)
        LOG.debug("Url $url got shortened to $customDomain/$encodedKey")
        return "$customDomain/$encodedKey"
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(UrlConverter::class.java)
    }
}
