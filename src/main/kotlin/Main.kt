import com.ponykamni.imageprocessor.PdfConverter
import domain.DownloadStampsUseCase
import domain.GetStampsRecordsUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

                    val pdfPath = "${PathsProvider.getPdfFolderName()}/$name.pdf"
                    val imagePath =
                        "${PathsProvider.getImagesFolderName()}/${name}${PathsProvider.getImagesSuffix()}.png"
                    val squarePath =
                        "${PathsProvider.getSquareImagesFolderName()}/${name}${PathsProvider.getSquareImagesSuffix()}.png"

                    PdfConverter().processSingleDocument(
                        pdfPath,
                        imagePath,
                        squarePath
                    )
                }

            }
        }
    }
}