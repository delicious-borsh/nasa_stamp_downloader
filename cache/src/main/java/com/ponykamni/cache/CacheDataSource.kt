package com.ponykamni.cache

import com.ponykamni.path.PathsProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ponykamni.entity.StampMessageID
import com.ponykamni.entity.StampRecord
import java.io.File
import java.io.OutputStreamWriter


class CacheDataSource {

    private val itemType = object : TypeToken<List<StampRecord>>() {}.type

    private val cache = HashMap<StampMessageID, StampRecord>()

    fun loadCache() {
        val file = File(FILE_NAME)

        if (file.exists()) {
            val inputStream = file.inputStream()

            val json = Gson().fromJson<List<StampRecord>>(inputStream.reader(), itemType)

            cache.clear()
            cache.putAll(json.toMap())
        }
    }

    fun updateCache(stampRecords: List<StampRecord>) {
        if (!File(path).exists()) {
            File(path).mkdir()
        }

        File(FILE_NAME).outputStream().use { os ->
            val json = Gson().toJson(stampRecords)
            val writer = OutputStreamWriter(os)
            writer.append(json)
            writer.close()
        }

    }

    fun getFromCache(stampMessageId: StampMessageID?): StampRecord? {
        return cache[stampMessageId]
    }

    fun getAllFromCache(): List<StampRecord> = cache.map { it.value }

    private fun List<StampRecord>.toMap(): Map<StampMessageID, StampRecord> =
        this.associateBy { it.id }

    companion object {
        private val path = PathsProvider.getCacheFolderName()

        private val FILE_NAME = "$path/stamp_records.json"
    }
}