package data.entity

import data.IMAGES_FOLDER_NAME

data class StampRecord(
    val id: StampMessageID,
    val fileName: String,
    val sharedFileName: String,
) {

    fun getFullPath(): String = "$IMAGES_FOLDER_NAME/$fileName.pdf"
}