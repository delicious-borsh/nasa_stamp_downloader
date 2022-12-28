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
            sharedName,
            stampDto.missionName
        )
    }

    suspend fun downloadFileForStamp(stampRecord: StampRecord) { //TODO why this methods prevents main from finishing
        Logger.log(LOG_TAG, "Loading file for ${stampRecord.fileName}")

        if (File(stampRecord.getFullPath()).exists()) {
            Logger.log(LOG_TAG, "File ${stampRecord.fileName} already exists, skipping")
        } else {
            val body = retrofitDataSource.getFile(stampRecord.sharedFileName, stampRecord.fileName)

            body.let {
                fileDataSource.saveFile(it, stampRecord.getFullPath())
            }
        }
    }

    companion object {

        private const val LOG_TAG = "StampRepository"
    }
}