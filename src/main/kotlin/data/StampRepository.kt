package data

import data.entity.StampDto
import data.entity.StampRecord
import data.network.RetrofitDataSource
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

    fun downloadFileForStamp(stampRecord: StampRecord) {//TODO WHY not finish with this method
        Logger.log(LOG_TAG, "Loading file for ${stampRecord.fileName}")
        retrofitDataSource.getFile(stampRecord.sharedFileName, stampRecord.fileName)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Logger.log(LOG_TAG, "Loading finished for ${stampRecord.fileName}")
                    response.body()?.let {
                        fileDataSource.saveFile(it, stampRecord.getFullPath())
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