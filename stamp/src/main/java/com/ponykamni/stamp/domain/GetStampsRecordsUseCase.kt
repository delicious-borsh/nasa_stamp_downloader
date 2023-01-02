package com.ponykamni.stamp.domain

import com.ponykamni.cache.CacheRepository
import com.ponykamni.entity.StampRecord

class GetStampsRecordsUseCase {

    private val cacheRepository = CacheRepository()

    operator fun invoke(): List<StampRecord> {
        return cacheRepository.getAllFromCache()
    }
}