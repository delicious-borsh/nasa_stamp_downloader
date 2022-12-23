package data

import okhttp3.ResponseBody
import java.io.File
import java.io.InputStream

class FileDataSource {

    fun saveFile(responseBody: ResponseBody, filePath: String) {
        responseBody.byteStream().saveToFile(filePath)
    }

    private fun InputStream.saveToFile(filePath: String) = use { input ->
        if (!File(IMAGES_FOLDER_NAME).exists()) {
            !File(IMAGES_FOLDER_NAME).mkdir()
        }
        val file = File(filePath)
        if (file.exists()) {
            Logger.log(LOG_TAG, "File $filePath already exists, skipping")
            return
        }
        Logger.log(LOG_TAG, "Saving file to $filePath")
        file.outputStream().use { output ->
            input.copyTo(output)
            Logger.log(LOG_TAG, "File saving finished for $filePath")
        }
    }

    companion object {

        private const val LOG_TAG = "FileDataSource"
    }
}