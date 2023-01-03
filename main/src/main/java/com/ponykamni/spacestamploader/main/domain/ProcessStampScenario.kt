package com.ponykamni.spacestamploader.main.domain

import com.ponykamni.spacestamploader.entity.StampMessageID
import com.ponykamni.spacestamploader.logger.Logger
import com.ponykamni.spacestamploader.stamp.data.StampRepository
import javax.inject.Inject

internal class ProcessStampScenario @Inject constructor(
    private val prepareRecordForStampUseCase: PrepareRecordForStampUseCase,
    private val prepareImageForStampUseCase: PrepareImageForStampUseCase,
    private val stampRepository: StampRepository,
) {

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