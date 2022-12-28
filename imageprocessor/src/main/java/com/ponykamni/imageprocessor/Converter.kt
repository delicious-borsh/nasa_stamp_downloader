package com.ponykamni.imageprocessor

import java.io.File

object Converter {

    private val imageSquarer = ImageSquarer()
    private val pdfToImageConverter = PdfToImageConverter()

    @JvmStatic
    fun main(args: Array<String>) {
        val path = "images/f_749107622913.pdf"
        val imagePath = "pdf-to-image/f_749107622913_0_image.png"
        val squarePath = "testsssss/f_749107622913_0_square.png"

        processSingleDocument(
            path,
            imagePath,
            squarePath
        )
    }


    private fun processSingleDocument(
        sourceFilePath: String,
        rawImageFilePath: String,
        squareImageFilePath: String,

    ) {
        val sourceFile = File(sourceFilePath)
        if (!sourceFile.exists()) {
            return
        }

//        createFolderFor(rawImageFilePath)
//        createFolderFor(squareImageFilePath)

        pdfToImageConverter.convert(sourceFilePath, rawImageFilePath)
        imageSquarer.squareImage(rawImageFilePath, squareImageFilePath)
    }


    private fun composeFinalImageFileName(sourceFile: File, suffix: String): String {
        val name = sourceFile.getNameWithoutExtension()
        val ext = sourceFile.extension
        return "$name$suffix.$ext"
    }


    private fun File.getNameWithoutExtension(): String {
        return this.name.split('.')[0]
    }


    private fun createFolderFor(filePath: String) {
        val file = File(filePath)
        if (!file.exists()) {
            file.mkdir()
        }
    }
}