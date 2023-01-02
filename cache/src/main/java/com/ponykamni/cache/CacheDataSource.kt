package com.ponykamni.cache

import com.google.gson.Gson
import com.ponykamni.entity.StampMessageID
import com.ponykamni.entity.StampRecord
import com.ponykamni.path.PathsProvider
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

    companion object {

        private val path = PathsProvider.getCacheFolderName()
    }
}