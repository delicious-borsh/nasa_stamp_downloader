package com.ponykamni.spacestamploader.main.domain

import com.ponykamni.spacestamploader.entity.StampMessageID
import com.ponykamni.spacestamploader.logger.Logger
import com.ponykamni.spacestamploader.stamp.data.StampRepository

class ProcessStampScenario {

    private val stampRepository = StampRepository()

    private val prepareRecordForStampUseCase = PrepareRecordForStampUseCase()
    private val prepareImageForStampUseCase = PrepareImageForStampUseCase()

    suspend operator fun invoke(id: StampMessageID) {

        Logger.log(LOG_TAG, "Processing stamp with id = $id")

        val record = prepareRecordForStampUseCase(id) ?: return

        if (!stampRepository.isStampFilePresent(record)) {
            stampRepository.downloadFileForStamp(record)
        }

        if (!stampRepository.isStampImagePresent(record)) {
            prepareImageForStampUseCase(record)
        }
    }

    companion object {

        private const val LOG_TAG = "ProcessStampScenario"
    }
}