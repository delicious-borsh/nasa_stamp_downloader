package data.gmail

import data.HardcodedKeyDataSource
import data.Logger
import data.Stamp

class GmailNasaRepository {

    private val gmailDataSource = GmailDataSource()
    private val hardcodedKeyDataSource = HardcodedKeyDataSource()

    fun getStamps(): List<Stamp> {
        val list = gmailDataSource.getMessageIdList(STAMP_QUERY)

        return list.mapIndexedNotNull { index, id ->
            Logger.log(LOG_TAG, "----------------- Processing stamp ${index + 1}/${list.size} ------------------")
            Logger.log(LOG_TAG, "Getting message for id $id")

            val message = gmailDataSource.getMessage(id.value)
            val wrapper = MessageWrapper(message)

            val body = wrapper.getBody() ?: return@mapIndexedNotNull null
            val stampUri =
                hardcodedKeyDataSource.getStampLink(body) ?: return@mapIndexedNotNull null
                    .also {
                        Logger.log(LOG_TAG, "Failed to create url for $id")
                    }

            Logger.log(LOG_TAG, "Got body for id $id")

            Stamp(
                wrapper.getId(),
                wrapper.getSubject() ?: "",
                stampUri
            ).also {
                Logger.log(LOG_TAG, "Got stamp $it")
            }

        }
    }

    companion object {
        private const val LOG_TAG = "GmailNasaRepository"
        private const val STAMP_QUERY = "from:<@nasa.gov> subject:stamp"
    }
}