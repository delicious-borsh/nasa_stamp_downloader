package com.ponykamni.imageprocessor

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.PDFRenderer
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO


object PdfToImageConverter {
    @JvmStatic
    fun main(args: Array<String>) {
        val path = "images/f_749107622913.pdf"
        val imagePath = "pdf-to-image/f_749107622913_0.png"
//        resterizePdf(path)
        getSquareImage(imagePath)
    }

    private fun resterizePdf(filePath: String) {
        try {
            val destinationDir = "pdf-to-image/"
            val sourceFile = File(filePath)
            val destinationFile = File(destinationDir)
            if (!destinationFile.exists()) {
                destinationFile.mkdir()
                println("Folder Created -> " + destinationFile.absolutePath)
            }
            if (sourceFile.exists()) {

                PDDocument.load(sourceFile).use { document ->

                    val pdfRenderer = PDFRenderer(document)
                    val fileName = sourceFile.name.replace(".pdf", "")
                    for (pageNumber in 0 until document.numberOfPages) {
                        val bim = pdfRenderer.renderImageWithDPI(pageNumber, 1000f)
                        val destDir = destinationDir + fileName + "_" + pageNumber + ".png"
                        ImageIO.write(bim, "png", File(destDir))
                    }
                }
                println("Image saved at -> " + destinationFile.absolutePath)

            } else {
                System.err.println(sourceFile.name + " File does not exist")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getSquareImage(filePath: String) {
        val croppedImagePath = "pdf-to-image/f_749107622913_square.png"
        val sourceFile = File(filePath)

        val sourceImage: BufferedImage = ImageIO.read(sourceFile)

        val croppedNewImage = cropImage(sourceImage, sourceImage.height, sourceImage.height)

        ImageIO.write(croppedNewImage, "png", File(croppedImagePath))
    }

    private fun cropImage(src: BufferedImage, width: Int, height: Int): BufferedImage? {
        val startingX = calculateStartingXForSquareCrop(src.width, src.height)
        return src.getSubimage(startingX, 0, width, height)
    }

    private fun calculateStartingXForSquareCrop(srcWidth: Int, srcHeight: Int): Int {
        val dimenDiff = srcWidth - srcHeight
        return dimenDiff / 2
    }
}