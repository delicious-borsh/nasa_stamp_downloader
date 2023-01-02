package com.ponykamni.spacestamploader.entity

data class StampDto(
    val id: StampMessageID,
    val stampUrl: StampUri,
    val missionName: String? = null,
)