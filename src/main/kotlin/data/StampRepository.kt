package data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StampRepository {

    private val retrofitDataSource = RetrofitDataSource()
    private val fileDataSource = FileDataSource()
    private val hardcodedKeyDataSource = HardcodedKeyDataSource()

    suspend fun resolveStampInfo(stampDto: StampDto): StampRecord {
        val string = retrofitDataSource.get(stampDto.stampUrl.value.toString())

        val fileName = hardcodedKeyDataSource.getFileName(string)

        Logger.log(LOG_TAG, "File $fileName is from message ${stampDto.id}")

        val sharedName = hardcodedKeyDataSource.getSharedName(string)

        return StampRecord(
            stampDto.id,
            fileName,
            sharedName
        )
    }

    fun downloadFileForStamp(stampRecord: StampRecord) {
        Logger.log(LOG_TAG, "Loading file for ${stampRecord.fileName}")
        retrofitDataSource.getFile(stampRecord.sharedFileName, stampRecord.fileName)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Logger.log(LOG_TAG, "Loading finished for  ${stampRecord.fileName}")
                    response.body()?.let {
                        fileDataSource.saveFile(it, stampRecord.fileName)
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Logger.log(LOG_TAG, "Failed to load file for ${stampRecord.fileName}")
                }
            })
    }

    companion object {

        private const val LOG_TAG = "StampRepository"
    }
}