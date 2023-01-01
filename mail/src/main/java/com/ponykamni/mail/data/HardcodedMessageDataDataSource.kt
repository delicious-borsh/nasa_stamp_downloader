package com.ponykamni.mail.data

import com.ponykamni.entity.StampUri
import java.net.URI

class HardcodedMessageDataDataSource {

    fun getMissionTitle(subject: String?): String? = subject?.let { getTitleFromSubject(it) }

    fun getStampLink(page: String): StampUri? {
        val urlString = getSubstringFromPage("We hope your", page)

        return try {
            StampUri(URI.create(urlString))
        } catch (ex: Exception) {
            null
        }
    }

    private fun getSubstringFromPage(substring: String, page: String): String {
        val startIndexOld = indexOfKeyEnd(substring, page)
        val startIndex = indexOfNearestQuotes(startIndexOld, page)

        val endIndex = indexOfNearestQuotes(startIndex + 1, page)

        return page.substring(startIndex + 1, endIndex)
    }

    private fun indexOfNearestQuotes(startIndex: Int, page: String): Int {
        return page.indexOf('"', startIndex)
    }

    private fun indexOfKeyEnd(substring: String, page: String): Int {
        return page.indexOf(substring) + substring.length + 1
    }

    private fun getTitleFromSubject(subject: String): String {
        val startIndex = subject.indexOf(string = "your", ignoreCase = true) + "your".length + 1
        val oneEndIndex = subject.indexOf(string = "virtual", ignoreCase = true) - 1
        val anotherEndIndex = subject.indexOf(string = "participation", ignoreCase = true) - 1

        val endIndex = if (oneEndIndex < 0) {
            anotherEndIndex
        } else {
            oneEndIndex
        }

        return subject.substring(startIndex, endIndex)
    }
}