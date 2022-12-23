package data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface SomeStrangeApi {

    @GET()
    suspend fun get(@Url url: String): String

}
