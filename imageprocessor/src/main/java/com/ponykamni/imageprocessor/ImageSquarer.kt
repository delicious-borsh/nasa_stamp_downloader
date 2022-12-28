package com.ponykamni.imageprocessor

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ImageSquarer {

    fun squareImage(sourceFilePath: String, destFilePath: String) {

        val sourceFile = File(sourceFilePath)
        if (!sourceFile.exists()) {
            return
        }

        val destFile = File(destFilePath)
        if (destFile.exists()) {
            return
        }

        createSquareImage(sourceFile, destFile)
    }

    private fun createSquareImage(sourceFile: File, destFile: File) {
        val sourceImage: BufferedImage = ImageIO.read(sourceFile)
        val croppedImage = cropImage(sourceImage, sourceImage.height, sourceImage.height)

        ImageIO.write(croppedImage, sourceFile.extension, destFile)
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