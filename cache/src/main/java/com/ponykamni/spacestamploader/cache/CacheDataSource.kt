package com.ponykamni.spacestamploader.cache

import com.google.gson.Gson
import com.ponykamni.spacestamploader.entity.StampMessageID
import com.ponykamni.spacestamploader.entity.StampRecord
import com.ponykamni.spacestamploader.path.PathsProvider
import java.io.File
import java.io.OutputStreamWriter


class CacheDataSource {

    private val gson = Gson()

    fun putToCache(record: StampRecord) {
        ensureFolderExists()

        val file = File(record.id.toPath())

        file.outputStream().use { os ->
            val json = gson.toJson(record)
            val writer = OutputStreamWriter(os)
            writer.append(json)
            writer.close()
        }

    }

    private fun ensureFolderExists() {
        val file = File(path)
        if (!file.exists()) {
            file.mkdir()
        }
    }

    fun getFromCache(id: StampMessageID): StampRecord? = File(id.toPath()).readIfExistsOrNull()

    fun getAllFromCache(): List<StampRecord> {
        return File(path).listFiles()?.mapNotNull { file ->
            file.readIfExistsOrNull()
        } ?: emptyList()
    }

    fun getCachedFilesList(): List<StampMessageID> {
        return File(path).listFiles()?.mapNotNull { file ->
            parseNameForId(file.name)
        } ?: emptyList()
    }

    private fun File.readIfExistsOrNull(): StampRecord? =
        if (this.exists()) {
            try {
                val inputStream = this.inputStream()
                gson.fromJson(inputStream.reader(), StampRecord::class.java)
            } catch (ex: Exception) {
                null
            }
        } else {
            null
        }

    private fun StampMessageID.toPath(): String = "$path/${this.value}.json"

    private fun parseNameForId(name: String): StampMessageID? {
        val idString = name.split('.').firstOrNull() ?: return null
        return StampMessageID(idString)
    }

    companion object {

        private val path = PathsProvider.getCacheFolderName()
    }
}