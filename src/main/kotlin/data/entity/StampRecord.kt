package data.entity

import PathsProvider

data class StampRecord(
    val id: StampMessageID,
    val fileName: String,
    val sharedFileName: String,
) {

    fun getFullPath(): String = "${PathsProvider.getPdfFolderName()}/$fileName.pdf"
}