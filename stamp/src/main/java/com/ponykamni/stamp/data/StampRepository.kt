package com.ponykamni.stamp.data

import com.ponykamni.entity.StampDto
import com.ponykamni.entity.StampRecord
import com.ponykamni.logger.Logger
import com.ponykamni.path.PathsProvider
import com.ponykamni.stamp.data.network.RemoteFileDataSource
import com.ponykamni.stamp.data.network.RemotePageDataSource
import java.io.File

class StampRepository {

    private val remoteFileDataSource = RemoteFileDataSource()
    private val remotePageDataSource = RemotePageDataSource()
    private val fileDataSource = FileDataSource()
    private val hardcodedPageDataDataSource = HardcodedPageDataDataSource()

    suspend fun resolveStampInfo(stampDto: StampDto): StampRecord {
        val string = remotePageDataSource.get(stampDto.stampUrl.value.toString())

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
            val body =
                remoteFileDataSource.getFile(stampRecord.sharedFileName, stampRecord.fileName)

            body.let {
                fileDataSource.saveFile(it, stampRecord.getFullPath())
            }
        }
    }

    private fun StampRecord.getFullPath(): String =
        "${PathsProvider.getPdfFolderName()}/$fileName.pdf"

    companion object {

        private const val LOG_TAG = "StampRepository"
    }
}