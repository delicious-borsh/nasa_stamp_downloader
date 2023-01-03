package com.ponykamni.spacestamploader.stamp.data.network

import retrofit2.http.GET
import retrofit2.http.Url

internal interface SomeStrangeApi {

    @GET()
    suspend fun get(@Url url: String): String
}
