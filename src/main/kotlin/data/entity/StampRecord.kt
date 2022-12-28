package data.entity

import PathsProvider

data class StampRecord(
    val id: StampMessageID,
    val fileName: String,
    val sharedFileName: String,
    val missionName: String? = null,
) {

    fun getFullPath(): String = "${PathsProvider.getPdfFolderName()}/$fileName.pdf"
}