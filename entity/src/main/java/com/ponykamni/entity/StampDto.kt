package com.ponykamni.entity

data class StampDto(
    val id: StampMessageID,
    val stampUrl: StampUri,
    val missionName: String? = null,
)