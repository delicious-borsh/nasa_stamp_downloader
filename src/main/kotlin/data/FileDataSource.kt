package data

import okhttp3.ResponseBody
import java.io.File
import java.io.InputStream

class FileDataSource {

    fun saveFile(responseBody: ResponseBody, fileName: String) {
        responseBody.byteStream().saveToFile("$FOLDER_NAME/$fileName.pdf")
    }

    private fun InputStream.saveToFile(filePath: String) = use { input ->
        if (!File(FOLDER_NAME).exists()) {
            !File(FOLDER_NAME).mkdir()
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
        private const val FOLDER_NAME = "images"
    }
}