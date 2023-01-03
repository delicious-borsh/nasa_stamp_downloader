package com.ponykamni.spacestamploader.main.domain

import com.ponykamni.spacestamploader.cache.CacheRepository
import com.ponykamni.spacestamploader.entity.StampRecord
import javax.inject.Inject

internal class GetStampsRecordsUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
) {

    operator fun invoke(): List<StampRecord> {
        return cacheRepository.getAllFromCache()
    }
}