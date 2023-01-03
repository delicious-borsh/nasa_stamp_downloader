package com.ponykamni.spacestamploader.main.domain

import com.ponykamni.spacestamploader.entity.StampRecord
import com.ponykamni.spacestamploader.logger.Logger
import com.ponykamni.spacestamploader.main.api.Stamp
import com.ponykamni.spacestamploader.main.api.StampId
import com.ponykamni.spacestamploader.main.api.StampTitle
import com.ponykamni.spacestamploader.stamp.data.StampRepository

class ValidateStampUseCase {

    private val stampRepository = StampRepository()

    operator fun invoke(stampRecord: StampRecord): Stamp? {

        Logger.log(LOG_TAG, "Validating stamp $stampRecord")

        if (!stampRepository.isStampFilePresent(stampRecord)) {
            Logger.log(LOG_TAG, "No PDF for $stampRecord. Not valid.")
            return null
        }

        if (!stampRepository.isStampImagePresent(stampRecord)) {
            Logger.log(LOG_TAG, "No picture for $stampRecord. Not valid.")
            return null
        }

        return stampRecord.toStamp()
            .also {
                Logger.log(LOG_TAG, "Record $stampRecord is valid. Got Stamp $it")
            }
    }

    private fun StampRecord.toStamp() = Stamp(
        StampId(this.fileName),
        StampTitle(this.missionName)
    )

    companion object {

        private const val LOG_TAG = "ValidateStampUseCase"
    }
}