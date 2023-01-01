package com.ponykamni.stamp.data

object Logger {

    fun log(tag: String, message: String, exception: Exception? = null) {
        println("$tag: $message")
    }
}