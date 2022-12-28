import com.ponykamni.imageprocessor.PdfConverter
import data.Logger
import data.StampRepository
import data.cache.CacheRepository
import data.gmail.GmailNasaRepository
import domain.DownloadStampsUseCase
import domain.GetStampsRecordsUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

class Main {

    companion object {

        private const val LOG_TAG = "Main"

        private val downloadStampsUseCase = DownloadStampsUseCase()
        private val getStampsUseCase = GetStampsRecordsUseCase()

        @JvmStatic
        fun main(args: Array<String>): Unit = runBlocking {
            launch {

//                downloadStampsUseCase()

                val path = "images/f_749107622913.pdf"
                val imagePath = "pdf-to-image/f_749107622913_0_image.png"
                val squarePath = "testsssss/f_749107622913_0_square.png"

                PdfConverter().processSingleDocument(
                    path,
                    imagePath,
                    squarePath
                )

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