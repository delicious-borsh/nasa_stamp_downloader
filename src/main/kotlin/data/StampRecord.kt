package data

data class StampRecord(
    val id: StampMessageID,
    val fileName: String,
    val sharedFileName: String,
) {

    fun getFullPath(): String = "$IMAGES_FOLDER_NAME/$fileName.pdf"
}