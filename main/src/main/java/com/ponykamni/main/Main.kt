package com.ponykamni.main

import com.ponykamni.imageprocessor.PdfConverter
import com.ponykamni.path.PathsProvider
import com.ponykamni.main.domain.DownloadStampsUseCase
import com.ponykamni.main.domain.GetStampsRecordsUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

class Main {

    companion object {

        private const val LOG_TAG = "com.ponykamni.main.Main"

        private val downloadStampsUseCase = DownloadStampsUseCase()
        private val getStampsUseCase = GetStampsRecordsUseCase()

        @JvmStatic
        fun main(args: Array<String>): Unit = runBlocking {
            launch {

                val filesFolder = File(PathsProvider.getFilesFolderName())

                if (!filesFolder.exists()) {
                    filesFolder.mkdir()
                }

                downloadStampsUseCase()

                val records = getStampsUseCase()

                for (record in records) {
                    val name = record.fileName

                    val pdfPath = PathsProvider.getPdfFilePath(name)
                    val imagePath = PathsProvider.getImageFilePath(name)
                    val squarePath = PathsProvider.getSquareImageFilePath(name)

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