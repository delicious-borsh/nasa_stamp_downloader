package com.ponykamni.spacestamploader.cache

import com.ponykamni.spacestamploader.entity.StampMessageID
import com.ponykamni.spacestamploader.entity.StampRecord

class CacheRepository {

    private val cacheDataSource = CacheDataSource()

    fun putToCache(stampRecord: StampRecord) {
        cacheDataSource.putToCache(stampRecord)
    }

    fun getFromCache(stampMessageId: StampMessageID): StampRecord? =
        cacheDataSource.getFromCache(stampMessageId)

    fun getAllFromCache(): List<StampRecord> = cacheDataSource.getAllFromCache()
}