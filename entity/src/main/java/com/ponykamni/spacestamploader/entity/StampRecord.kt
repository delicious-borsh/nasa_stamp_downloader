package com.ponykamni.spacestamploader.entity

data class StampRecord(
    val id: StampMessageID,
    val fileName: String,
    val sharedFileName: String,
    val missionName: String? = null,
) {
}