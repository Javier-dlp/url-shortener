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
        log.debug("Shortening url $url")
        val key: Int = dictionary.getKey(url)
        val encodedKey: String = coder.encode(key)
        val fullUrl = "$customDomain/$encodedKey"
        log.debug("Url $url got shortened to $fullUrl")
        return fullUrl
    }

    fun resolve(url: String): String? {
        log.debug("Resolving url $url")
        val key: String = extractKey(url)
        val decoded: Int? = coder.decode(key)
        val resolved: String? = decoded?.let { dictionary.getUrl(it) }
        log.debug("Url $url got resolved to $resolved")
        return resolved
    }

    private fun extractKey(url: String): String = url.removePrefix(customDomain)
        .removePrefix("/")
        .removeSuffix("/")

    companion object {
        private val log = LoggerFactory.getLogger(UrlConverter::class.java)
    }
}
