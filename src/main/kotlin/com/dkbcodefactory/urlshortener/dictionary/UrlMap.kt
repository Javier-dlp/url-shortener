package com.dkbcodefactory.urlshortener.dictionary

import org.springframework.stereotype.Component
import java.util.*

@Component
class UrlMap: UrlDictionary {

    var items = TreeMap<Int, String>()

    override fun getKey(url: String): Int {
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

    override fun getUrl(key: Int): String? {
        return items[key]
    }
}
