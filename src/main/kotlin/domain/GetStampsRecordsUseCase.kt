package domain

import data.cache.CacheRepository
import data.entity.StampRecord

class GetStampsRecordsUseCase {

    private val cacheRepository = CacheRepository()

    suspend operator fun invoke(): List<StampRecord> {
        cacheRepository.loadCache()
        return cacheRepository.getAllFromCache()
    }
}