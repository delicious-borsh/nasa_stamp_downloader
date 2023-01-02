package com.ponykamni.spacestamploader.stamp.data

class HardcodedPageDataDataSource {

    fun getFileName(page: String): String = getSubstringFromPage("{\"items\":[{\"typedID\":", page)

    fun getSharedName(page: String): String = getSubstringFromPage("{\"sharedName\":", page)

    private fun getSubstringFromPage(substring: String, page: String): String {
        val startIndex = indexOfKeyEnd(substring, page)
        val endIndex = indexOfNearestQuotes(startIndex, page)

        return page.substring(startIndex, endIndex)
    }

    private fun indexOfNearestQuotes(startIndex: Int, page: String): Int {
        return page.indexOf('"', startIndex)
    }

    private fun indexOfKeyEnd(substring: String, page: String): Int {
        return page.indexOf(substring) + substring.length + 1
    }
}