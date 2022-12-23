package data.network

import retrofit2.http.*

interface SomeStrangeApi {

    @GET()
    suspend fun get(@Url url: String): String

}
