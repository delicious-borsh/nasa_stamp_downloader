package data.network

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitDataSource() {

    private val boxNasaApi: BoxNasaApi = Retrofit
        .Builder()
        .baseUrl("https://nasa-external-ocomm.app.box.com/")
        .client(OkHttpClient())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
        .create(BoxNasaApi::class.java)

    private val someStrangeApi: SomeStrangeApi = Retrofit
        .Builder()
        .baseUrl("https://nasa-external-ocomm.app.box.com/")
        .client(OkHttpClient())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
        .create(SomeStrangeApi::class.java)

    suspend fun getStampPage(sharedName: String): String = boxNasaApi.getStampPage(sharedName)

    fun getFile(sharedName: String, fileId: String): Call<ResponseBody> =
        boxNasaApi.downloadFile(sharedName, fileId)

    suspend fun get(url: String): String = someStrangeApi.get(url)
}