package com.ponykamni.spacestamploader.mail.data

import com.ponykamni.spacestamploader.entity.StampDto
import com.ponykamni.spacestamploader.entity.StampMessageID
import com.ponykamni.spacestamploader.logger.Logger

class GmailNasaRepository {

    private val gmailDataSource = GmailDataSource()
    private val hardcodedMessageDataDataSource = HardcodedMessageDataDataSource()

    fun getMessages(): List<StampMessageID> = gmailDataSource.getMessageIdList(STAMP_QUERY)

    fun getRemoteMessage(stampMessageID: StampMessageID): StampDto? {
        val message = gmailDataSource.getMessage(stampMessageID.value)
        val wrapper = MessageWrapper(message)

        val body = wrapper.getBody() ?: return null
        val stampUri =
            hardcodedMessageDataDataSource.getStampLink(body) ?: return null
                .also {
                    Logger.log(LOG_TAG, "Failed to create url for $stampMessageID")
                }

        Logger.log(LOG_TAG, "Got body for messageID $stampMessageID")

        val title = hardcodedMessageDataDataSource.getMissionTitle(wrapper.getSubject())

        return StampDto(stampMessageID, stampUri, title).also {
            Logger.log(LOG_TAG, "Got stamp $it")
        }
    }

    companion object {
        private const val LOG_TAG = "GmailNasaRepository"
        private const val STAMP_QUERY = "from:<@nasa.gov> subject:stamp"
    }
}