package com.ponykamni.spacestamploader.stamp.data

import com.ponykamni.spacestamploader.entity.StampDto
import com.ponykamni.spacestamploader.entity.StampRecord
import com.ponykamni.spacestamploader.logger.Logger
import com.ponykamni.spacestamploader.path.PathsProvider
import com.ponykamni.spacestamploader.stamp.data.network.RemoteFileDataSource
import com.ponykamni.spacestamploader.stamp.data.network.RemotePageDataSource
import java.io.File
import javax.inject.Inject

internal class StampRepositoryImpl @Inject constructor(
    private val remoteFileDataSource: RemoteFileDataSource,
    private val remotePageDataSource: RemotePageDataSource,
    private val fileDataSource: FileDataSource,
    private val hardcodedPageDataDataSource: HardcodedPageDataDataSource,
) : StampRepository {

    override suspend fun resolveStampInfo(stampDto: StampDto): StampRecord {
        val string = remotePageDataSource.get(stampDto.stampUrl.value.toString())

        val fileName = hardcodedPageDataDataSource.getFileName(string)

        Logger.log(LOG_TAG, "File $fileName is from message ${stampDto.id}")

        val sharedName = hardcodedPageDataDataSource.getSharedName(string)

        return StampRecord(
            stampDto.id,
            fileName,
            sharedName,
            stampDto.missionName ?: ""
        )
    }

    override suspend fun downloadFileForStamp(stampRecord: StampRecord) { //TODO why this methods prevents main from finishing
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

    override fun isStampFilePresent(stampRecord: StampRecord): Boolean =
        fileDataSource.isFilePresent(stampRecord.getFullPath())

    override fun isStampImagePresent(stampRecord: StampRecord): Boolean =
        fileDataSource.isFilePresent(stampRecord.getSquareImageFullPath())

    private fun StampRecord.getFullPath(): String = PathsProvider.getPdfFilePath(fileName)

    private fun StampRecord.getSquareImageFullPath(): String =
        PathsProvider.getSquareImageFilePath(fileName) //not exactly this module's domain but who cares

    companion object {

        private const val LOG_TAG = "StampRepository"
    }
}