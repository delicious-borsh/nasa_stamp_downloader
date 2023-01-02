package com.ponykamni.spacestamploader.stamp.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class RemotePageDataSource() {

    private val someStrangeApi: SomeStrangeApi = Retrofit
        .Builder()
        .baseUrl("https://nasa-external-ocomm.app.box.com/")
        .client(OkHttpClient())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
        .create(SomeStrangeApi::class.java)

    suspend fun get(url: String): String = someStrangeApi.get(url)
}