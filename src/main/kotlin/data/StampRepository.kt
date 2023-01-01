package data

import data.entity.StampDto
import data.entity.StampRecord
import data.network.RetrofitDataSource
import java.io.File

class StampRepository {

    private val retrofitDataSource = RetrofitDataSource()
    private val fileDataSource = FileDataSource()
    private val hardcodedPageDataDataSource = HardcodedPageDataDataSource()

    suspend fun resolveStampInfo(stampDto: StampDto): StampRecord {
        val string = retrofitDataSource.get(stampDto.stampUrl.value.toString())

        val fileName = hardcodedPageDataDataSource.getFileName(string)

        Logger.log(LOG_TAG, "File $fileName is from message ${stampDto.id}")

        val sharedName = hardcodedPageDataDataSource.getSharedName(string)

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