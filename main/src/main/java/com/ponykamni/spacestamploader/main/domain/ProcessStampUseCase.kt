package com.ponykamni.spacestamploader.main.domain

import com.ponykamni.spacestamploader.cache.CacheRepository
import com.ponykamni.spacestamploader.entity.StampMessageID
import com.ponykamni.spacestamploader.imageprocessor.PdfConverter
import com.ponykamni.spacestamploader.logger.Logger
import com.ponykamni.spacestamploader.mail.data.GmailNasaRepository
import com.ponykamni.spacestamploader.path.PathsProvider
import com.ponykamni.spacestamploader.stamp.data.StampRepository

class ProcessStampUseCase {

    private val gmailRepository = GmailNasaRepository()
    private val stampRepository = StampRepository()
    private val cacheRepository = CacheRepository()

    private val pdfConverter = PdfConverter()

    suspend operator fun invoke(id: StampMessageID) {

        Logger.log(LOG_TAG, "Processing stamp with id = $id")

        val record = cacheRepository.getFromCache(id) ?: gmailRepository.getRemoteMessage(id)?.let {
            val record = stampRepository.resolveStampInfo(it)
            cacheRepository.putToCache(record)
            record
        }

        record ?: return

        stampRepository.downloadFileForStamp(record)

        val name = record.fileName

        val pdfPath = PathsProvider.getPdfFilePath(name)
        val imagePath = PathsProvider.getImageFilePath(name)
        val squarePath = PathsProvider.getSquareImageFilePath(name)

        pdfConverter.processSingleDocument(
            pdfPath,
            imagePath,
            squarePath
        )
    }

    companion object {

        private const val LOG_TAG = "DownloadStampsUseCase"
    }
}