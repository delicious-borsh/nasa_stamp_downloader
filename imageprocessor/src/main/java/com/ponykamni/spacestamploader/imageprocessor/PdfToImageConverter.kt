package com.ponykamni.spacestamploader.imageprocessor

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.PDFRenderer
import java.io.File
import javax.imageio.ImageIO


class PdfToImageConverter {

    fun convert(sourceFilePath: String, destFilePath: String) {
        val sourceFile = File(sourceFilePath)
        if (!sourceFile.exists()) {
            return
        }

        val destFile = File(destFilePath)
        if (destFile.exists()) {
            return
        }

        rasterizePdf(sourceFilePath, destFilePath)
    }

    private fun rasterizePdf(sourceFilePath: String, destFilePath: String) {
        try {
            val sourceFile = File(sourceFilePath)
            val destFile = File(destFilePath)

            PDDocument.load(sourceFile).use { document ->

                val pdfRenderer = PDFRenderer(document)

                for (pageNumber in 0 until document.numberOfPages) {
                    val bim = pdfRenderer.renderImageWithDPI(pageNumber, 1000f)
                    ImageIO.write(bim, "png", destFile)
                }
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}