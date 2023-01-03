package com.ponykamni.spacestamploader.stamp.data

import com.ponykamni.spacestamploader.entity.StampDto
import com.ponykamni.spacestamploader.entity.StampRecord

interface StampRepository {

    suspend fun resolveStampInfo(stampDto: StampDto): StampRecord
    suspend fun downloadFileForStamp(stampRecord: StampRecord)
    fun isStampFilePresent(stampRecord: StampRecord): Boolean
    fun isStampImagePresent(stampRecord: StampRecord): Boolean
}