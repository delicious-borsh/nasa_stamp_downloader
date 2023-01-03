package com.ponykamni.spacestamploader.cache

import com.ponykamni.spacestamploader.entity.StampMessageID
import com.ponykamni.spacestamploader.entity.StampRecord
import javax.inject.Inject

internal class CacheRepositoryImpl @Inject constructor(
    private val cacheDataSource: CacheDataSource,
) : CacheRepository {

    override fun putToCache(stampRecord: StampRecord) {
        cacheDataSource.putToCache(stampRecord)
    }

    override fun getFromCache(stampMessageId: StampMessageID): StampRecord? =
        cacheDataSource.getFromCache(stampMessageId)

    override fun getAllFromCache(): List<StampRecord> = cacheDataSource.getAllFromCache()

    override fun getCachedIds(): List<StampMessageID> = cacheDataSource.getCachedFilesList()
}