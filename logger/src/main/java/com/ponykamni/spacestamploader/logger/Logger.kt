package com.ponykamni.spacestamploader.logger

object Logger {

    fun log(tag: String, message: String, exception: Exception? = null) {
        println("$tag: $message")
    }
}