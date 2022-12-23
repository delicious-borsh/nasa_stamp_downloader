package data

import okhttp3.ResponseBody
import java.io.File
import java.io.InputStream

class FileDataSource {

    fun saveFile(responseBody: ResponseBody, filePath: String) {
        Logger.log(LOG_TAG, "Saving file to $filePath")

        responseBody.byteStream().saveToFile(filePath)
    }

    private fun InputStream.saveToFile(fileName: String) = use { input ->
        val file = File(fileName)
        if (file.exists()) {
            Logger.log(LOG_TAG, "File $fileName already exists, skipping")
            return
        }
        file.outputStream().use { output ->
            input.copyTo(output)
            Logger.log(LOG_TAG, "File saving finished for $fileName")
        }
    }

    companion object {

        private const val LOG_TAG = "FileDataSource"

    }
}