package data.network

import okhttp3.ResponseBody
import retrofit2.http.*

interface BoxNasaApi {

    @Streaming
    @GET("index.php")
    suspend fun downloadFile(
        @Query("shared_name") sharedName: String,
        @Query("file_id") fileId: String,
        @Query("rm") rm: String = "box_download_shared_file",
    ): ResponseBody
}
