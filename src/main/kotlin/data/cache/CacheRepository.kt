package data.cache

import data.entity.StampMessageID
import data.entity.StampRecord

class CacheRepository {

    private val cacheDataSource = CacheDataSource()

    fun loadCache() {
        cacheDataSource.loadCache()
    }

    fun updateCache(stampRecords: List<StampRecord>) {
        cacheDataSource.updateCache(stampRecords)
    }

    fun getFromCache(stampMessageId: StampMessageID?): StampRecord? =
        cacheDataSource.getFromCache(stampMessageId)

    fun getAllFromCache(): List<StampRecord> = cacheDataSource.getAllFromCache()
}