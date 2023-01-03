package com.ponykamni.spacestamploader.imageprocessor

import java.io.File
import javax.inject.Inject

internal class PdfConverterImpl @Inject constructor(
    private val imageSquarer: ImageSquarer,
    private val pdfToImageConverter: PdfToImageConverter,
) : PdfConverter {

    override fun processSingleDocument(
        sourceFilePath: String,
        rawImageFilePath: String,
        squareImageFilePath: String,
    ) {
        val sourceFile = File(sourceFilePath)
        if (!sourceFile.exists()) {
            return
        }

        createFolderFor(rawImageFilePath)
        createFolderFor(squareImageFilePath)

        pdfToImageConverter.convert(sourceFilePath, rawImageFilePath)
        imageSquarer.squareImage(rawImageFilePath, squareImageFilePath)
    }

    private fun createFolderFor(filePath: String) {
        val file = File(filePath).parentFile
        if (!file.exists()) {
            file.mkdir()
        }
    }
}
