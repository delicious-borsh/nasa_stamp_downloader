package com.ponykamni.spacestamploader.main.domain

import com.ponykamni.spacestamploader.cache.CacheRepository
import com.ponykamni.spacestamploader.entity.StampMessageID
import com.ponykamni.spacestamploader.entity.StampRecord
import com.ponykamni.spacestamploader.logger.Logger
import com.ponykamni.spacestamploader.mail.data.GmailNasaRepository
import com.ponykamni.spacestamploader.stamp.data.StampRepository

class PrepareRecordForStampUseCase {

    private val gmailRepository = GmailNasaRepository()
    private val stampRepository = StampRepository()
    private val cacheRepository = CacheRepository()

    suspend operator fun invoke(id: StampMessageID): StampRecord? {

        Logger.log(LOG_TAG, "Processing stamp with id = $id")

        val record = cacheRepository.getFromCache(id) ?: gmailRepository.getRemoteMessage(id)?.let {
            val record = stampRepository.resolveStampInfo(it)
            cacheRepository.putToCache(record)
            record
        }

        return record
    }

    companion object {

        private const val LOG_TAG = "PrepareRecordForStampUseCase"
    }
}