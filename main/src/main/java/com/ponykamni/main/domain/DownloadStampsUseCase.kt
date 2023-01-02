package com.ponykamni.main.domain

import com.ponykamni.cache.CacheRepository
import com.ponykamni.logger.Logger
import com.ponykamni.mail.data.GmailNasaRepository
import com.ponykamni.stamp.data.StampRepository

class DownloadStampsUseCase {

    private val gmailRepository = GmailNasaRepository()
    private val stampRepository = StampRepository()
    private val cacheRepository = CacheRepository()

    suspend operator fun invoke() {
        val messages = gmailRepository.getMessages()

        val stampRecords = messages.mapIndexedNotNull { index, id ->
            Logger.log(
                LOG_TAG,
                "----------------- Processing stamp ${index + 1}/${messages.size} ------------------"
            )
            Logger.log(LOG_TAG, "Getting message for id $id")
            cacheRepository.getFromCache(id) ?: gmailRepository.getRemoteMessage(id)?.let {
                val record = stampRepository.resolveStampInfo(it)
                cacheRepository.putToCache(record)
                record
            }
        }

        stampRecords.forEach { stampRepository.downloadFileForStamp(it) }
    }

    companion object {

        private const val LOG_TAG = "DownloadStampsUseCase"
    }
}