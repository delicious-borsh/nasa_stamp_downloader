import data.Logger
import data.StampRepository
import data.cache.CacheRepository
import data.gmail.GmailNasaRepository
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
            }
        }
    }
}