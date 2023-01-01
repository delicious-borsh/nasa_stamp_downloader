package com.ponykamni.path

object PathsProvider {

    fun getPdfFolderName(): String = PDF_FOLDER_NAME
    fun getCacheFolderName(): String = CACHE_FOLDER_NAME
    fun getImagesFolderName(): String = IMAGES_FOLDER_NAME
    fun getSquareImagesFolderName(): String = SQUARE_IMAGES_FOLDER_NAME

    fun getImagesSuffix(): String = IMAGE_SUFFIX
    fun getSquareImagesSuffix(): String = SQUARE_IMAGE_SUFFIX

    private const val PDF_FOLDER_NAME = "pdf"
    private const val CACHE_FOLDER_NAME = "cache"
    private const val IMAGES_FOLDER_NAME = "images"
    private const val SQUARE_IMAGES_FOLDER_NAME = "squared"

    private const val IMAGE_SUFFIX = "_image"
    private const val SQUARE_IMAGE_SUFFIX = "_squared"
}