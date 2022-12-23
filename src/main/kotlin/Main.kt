import data.StampRepository
import data.gmail.GmailNasaRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Main {

    companion object {

        private val gmailRepository = GmailNasaRepository()
        private val stampRepository = StampRepository()

        @JvmStatic
        fun main(args: Array<String>): Unit = runBlocking {
            launch {

                val stamps = gmailRepository.getStamps()
                stampRepository.downloadAndSave(stamps)
            }
        }
    }
}