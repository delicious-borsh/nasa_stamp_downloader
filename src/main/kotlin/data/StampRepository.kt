package data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StampRepository {

    private val retrofitDataSource = RetrofitDataSource()
    private val fileDataSource = FileDataSource()
    private val hardcodedKeyDataSource = HardcodedKeyDataSource()

    suspend fun downloadAndSave(stamps: List<Stamp>) {
        for (index in stamps.indices) {
            Logger.log(
                LOG_TAG,
                "----------------- Processing file ${index + 1}/${stamps.size} ------------------"
            )
            downloadAndSave(stamps[index])
        }
    }

    private suspend fun downloadAndSave(stamp: Stamp) {
        val string = retrofitDataSource.get(stamp.stampUrl.toString())

        val id = hardcodedKeyDataSource.getId(string)

        Logger.log(LOG_TAG, "File $id is from message ${stamp.messageID}")

        val sharedName = hardcodedKeyDataSource.getSharedName(string)

        saveFile(sharedName, id)
    }

    private fun saveFile(sharedName: String, fileId: String) {
        Logger.log(LOG_TAG, "Loading file for $fileId")
        retrofitDataSource.getFile(sharedName, fileId).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                Logger.log(LOG_TAG, "Loading finished for $fileId")
                response.body()?.let {
                    fileDataSource.saveFile(it, "images/$fileId.pdf")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Logger.log(LOG_TAG, "Failed to load file for $fileId")
            }
        })
    }

    companion object {

        private const val LOG_TAG = "StampRepository"
    }
}