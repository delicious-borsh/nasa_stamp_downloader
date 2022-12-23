package data.cache

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import data.CACHE_FOLDER_NAME
import data.entity.StampMessageID
import data.entity.StampRecord
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

            cache.putAll(json.toMap())
        }
    }

    fun updateCache(stampRecords: List<StampRecord>) {
        if (!File(CACHE_FOLDER_NAME).exists()) {
            !File(CACHE_FOLDER_NAME).mkdir()
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

    private fun List<StampRecord>.toMap(): Map<StampMessageID, StampRecord> =
        this.associateBy { it.id }

    companion object {

        private const val FILE_NAME = "$CACHE_FOLDER_NAME/stamp_records.json"
    }
}