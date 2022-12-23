package domain

import data.Logger
import data.StampRepository
import data.cache.CacheRepository
import data.gmail.GmailNasaRepository

class DownloadStampsUseCase {

    private val gmailRepository = GmailNasaRepository()
    private val stampRepository = StampRepository()
    private val cacheRepository = CacheRepository()

    suspend operator fun invoke() {
        cacheRepository.loadCache()

        val messages = gmailRepository.getMessages()

        val stampRecords = messages.mapIndexedNotNull { index, id ->
            Logger.log(
                LOG_TAG,
                "----------------- Processing stamp ${index + 1}/${messages.size} ------------------"
            )
            Logger.log(LOG_TAG, "Getting message for id $id")
            cacheRepository.getFromCache(id) ?: gmailRepository.getRemoteMessage(id)?.let {
                stampRepository.resolveStampInfo(it)
            }
        }

        stampRecords.forEach { stampRepository.downloadFileForStamp(it) }

        cacheRepository.updateCache(stampRecords)
    }

    companion object {

        private const val LOG_TAG = "DownloadStampsUseCase"
    }
}