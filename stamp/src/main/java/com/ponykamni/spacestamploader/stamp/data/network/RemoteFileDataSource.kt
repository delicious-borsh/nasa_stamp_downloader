package com.ponykamni.spacestamploader.stamp.data.network

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Inject

internal class RemoteFileDataSource @Inject constructor() {

    private val boxNasaApi: BoxNasaApi = Retrofit
        .Builder()
        .baseUrl("https://nasa-external-ocomm.app.box.com/")
        .client(OkHttpClient())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
        .create(BoxNasaApi::class.java)

    suspend fun getFile(sharedName: String, fileId: String): ResponseBody =
        boxNasaApi.downloadFile(sharedName, fileId)
}