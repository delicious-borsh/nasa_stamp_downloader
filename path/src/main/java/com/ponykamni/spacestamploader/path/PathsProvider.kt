package com.ponykamni.spacestamploader.path

object PathsProvider {

    fun getPdfFilePath(fileName: String): String = "$PDF_FOLDER_NAME/$fileName.pdf"

    fun getImageFilePath(fileName: String): String =
        "$IMAGES_FOLDER_NAME/$fileName$IMAGE_SUFFIX.png"

    fun getSquareImageFilePath(fileName: String): String =
        "$SQUARE_IMAGES_FOLDER_NAME/$fileName$SQUARE_IMAGE_SUFFIX.png"

    fun getFilesFolderName(): String = FILES_FOLDER_NAME
    fun getPdfFolderName(): String = PDF_FOLDER_NAME
    fun getCacheFolderName(): String = CACHE_FOLDER_NAME

    private const val FILES_FOLDER_NAME = "stampfiles"

    private const val PDF_FOLDER_NAME = "$FILES_FOLDER_NAME/pdf"
    private const val CACHE_FOLDER_NAME = "$FILES_FOLDER_NAME/cache"
    private const val IMAGES_FOLDER_NAME = "$FILES_FOLDER_NAME/images"
    private const val SQUARE_IMAGES_FOLDER_NAME = "$FILES_FOLDER_NAME/squared"

    private const val IMAGE_SUFFIX = "_image"
    private const val SQUARE_IMAGE_SUFFIX = "_squared"
}