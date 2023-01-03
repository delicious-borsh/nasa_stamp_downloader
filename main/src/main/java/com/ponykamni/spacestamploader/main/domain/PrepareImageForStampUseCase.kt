package com.ponykamni.spacestamploader.main.domain

import com.ponykamni.spacestamploader.entity.StampRecord
import com.ponykamni.spacestamploader.imageprocessor.PdfConverter
import com.ponykamni.spacestamploader.logger.Logger
import com.ponykamni.spacestamploader.path.PathsProvider
import javax.inject.Inject

class PrepareImageForStampUseCase @Inject constructor(
    private val pdfConverter: PdfConverter,
) {

    operator fun invoke(stampRecord: StampRecord) {

        Logger.log(LOG_TAG, "Preparing image for stamp $stampRecord")

        val name = stampRecord.fileName

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

        private const val LOG_TAG = "PrepareImageForStampUseCase"
    }
}