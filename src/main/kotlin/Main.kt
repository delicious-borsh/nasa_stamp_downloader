import com.ponykamni.imageprocessor.PdfConverter
import data.Logger
import data.StampRepository
import data.cache.CacheRepository
import data.gmail.GmailNasaRepository
import domain.DownloadStampsUseCase
import domain.GetStampsRecordsUseCase
import kotlinx.coroutines.*
import java.io.File

class Main {

    companion object {

        private const val LOG_TAG = "Main"

        private val downloadStampsUseCase = DownloadStampsUseCase()
        private val getStampsUseCase = GetStampsRecordsUseCase()

        @JvmStatic
        fun main(args: Array<String>): Unit = runBlocking {
            launch {

                downloadStampsUseCase()

                val records = getStampsUseCase()

                for (record in records) {
                    val name = record.fileName

                    val path = "images/$name.pdf"
                    val imagePath = "pdf-to-image/${name}_image.png"
                    val squarePath = "testsssss/${name}_square.png"

                    PdfConverter().processSingleDocument(
                        path,
                        imagePath,
                        squarePath
                    )
                }

            }
        }

        private fun composeFinalImageFileName(sourceFile: File, suffix: String): String {
            val name = sourceFile.getNameWithoutExtension()
            val ext = sourceFile.extension
            return "$name$suffix.$ext"
        }


        private fun File.getNameWithoutExtension(): String {
            return this.name.split('.')[0]
        }

    }
}