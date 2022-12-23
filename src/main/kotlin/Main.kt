import data.Logger
import data.StampRepository
import data.cache.CacheRepository
import data.gmail.GmailNasaRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Main {

    companion object {

        private const val LOG_TAG = "Main"

        private val gmailRepository = GmailNasaRepository()
        private val stampRepository = StampRepository()
        private val cacheRepository = CacheRepository()

        @JvmStatic
        fun main(args: Array<String>): Unit = runBlocking {
            launch {

                cacheRepository.loadCache()

                val messages = gmailRepository.getMessages()

                val stampRecords = messages.mapIndexedNotNull { index, id ->
                    Logger.log(
                        LOG_TAG,
                        "----------------- Processing stamp ${index + 1}/${messages.size} ------------------"
                    )
                    Logger.log(LOG_TAG, "Getting message for id $id")
                    cacheRepository.getFromCache(id) ?: gmailRepository.getRemoteMessage(id)?.let {
                        stampRepository.resolveStampInfo(it)
                    }
                }

                cacheRepository.updateCache(stampRecords)

                stampRecords.forEach {
                    stampRepository.downloadFileForStamp(it)
                }
            }
        }
    }
}