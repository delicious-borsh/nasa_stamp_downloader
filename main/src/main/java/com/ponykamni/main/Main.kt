package com.ponykamni.main

import com.ponykamni.imageprocessor.PdfConverter
import com.ponykamni.path.PathsProvider
import com.ponykamni.stamp.domain.DownloadStampsUseCase
import com.ponykamni.stamp.domain.GetStampsRecordsUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Main {

    companion object {

        private const val LOG_TAG = "com.ponykamni.main.Main"

        private val downloadStampsUseCase = DownloadStampsUseCase()
        private val getStampsUseCase = GetStampsRecordsUseCase()

        @JvmStatic
        fun main(args: Array<String>): Unit = runBlocking {
            launch {

                Companion.downloadStampsUseCase()

                val records = Companion.getStampsUseCase()

                for (record in records) {
                    val name = record.fileName

                    val pdfPath = "${PathsProvider.getPdfFolderName()}/$name.pdf"
                    val imagePath =
                        "${PathsProvider.getImagesFolderName()}/${name}${PathsProvider.getImagesSuffix()}.png"
                    val squarePath =
                        "${PathsProvider.getSquareImagesFolderName()}/${name}${PathsProvider.getSquareImagesSuffix()}.png"

                    PdfConverter().processSingleDocument(
                        pdfPath,
                        imagePath,
                        squarePath
                    )
                }

            }
        }
    }
}