package com.ponykamni.spacestamploader.cache

import com.ponykamni.spacestamploader.entity.StampMessageID
import com.ponykamni.spacestamploader.entity.StampRecord

interface CacheRepository {
    fun putToCache(stampRecord: StampRecord)
    fun getFromCache(stampMessageId: StampMessageID): StampRecord?
    fun getAllFromCache(): List<StampRecord>
    fun getCachedIds(): List<StampMessageID>
}