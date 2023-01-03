package com.ponykamni.spacestamploader.imageprocessor

interface PdfConverter {

    fun processSingleDocument(
        sourceFilePath: String,
        rawImageFilePath: String,
        squareImageFilePath: String,
    )
}