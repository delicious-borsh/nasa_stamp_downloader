package data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface BoxNasaApi {

    @GET("s/{shared_name}")
    suspend fun getStampPage(@Path("shared_name") sharedName: String): String

    @Streaming
    @GET("index.php")
    fun downloadFile(
        @Query("shared_name") sharedName: String,
        @Query("file_id") fileId: String,
        @Query("rm") rm: String = "box_download_shared_file",
    ): Call<ResponseBody>
}
