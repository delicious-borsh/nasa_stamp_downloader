package data

import data.entity.StampDto
import data.entity.StampRecord
import data.network.RetrofitDataSource
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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

    suspend fun downloadFileForStamp(stampRecord: StampRecord) = //TODO why this methods prevents main from finishing
        suspendCoroutine<Unit> { continuation ->
            Logger.log(LOG_TAG, "Loading file for ${stampRecord.fileName}")

            if (File(stampRecord.getFullPath()).exists()) {
                Logger.log(LOG_TAG, "File ${stampRecord.fileName} already exists, skipping")
                continuation.resume(Unit)
            } else {
                retrofitDataSource.getFile(stampRecord.sharedFileName, stampRecord.fileName)
                    .enqueue(
                        GetFileCallback(
                            continuation,
                            stampRecord,
                        )
                    )
            }
        }

    inner class GetFileCallback(
        private val continuation: Continuation<Unit>,
        private val stampRecord: StampRecord,
    ) : Callback<ResponseBody> {

        override fun onResponse(
            call: Call<ResponseBody>,
            response: Response<ResponseBody>
        ) {
            Logger.log(LOG_TAG, "Loading finished for ${stampRecord.fileName}")
            response.body()?.let {
                fileDataSource.saveFile(it, stampRecord.getFullPath())
            }

            continuation.resume(Unit)
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            Logger.log(LOG_TAG, "Failed to load file for ${stampRecord.fileName}")
            continuation.resumeWithException(t)
        }
    }

    companion object {

        private const val LOG_TAG = "StampRepository"
    }
}