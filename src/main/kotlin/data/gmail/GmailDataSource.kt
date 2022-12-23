package data.gmail

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.gmail.Gmail
import com.google.api.services.gmail.model.Message
import data.Logger
import data.StampMessageID

class GmailDataSource {

    private val jsonFactory: JsonFactory = GsonFactory.getDefaultInstance()
    private val authenticator = GoogleCloudAuthenticator(jsonFactory)

    private val httpTransport: NetHttpTransport = GoogleNetHttpTransport.newTrustedTransport()

    private val gmail: Gmail = createGmailService()

    private fun createGmailService(): Gmail =
        Gmail
            .Builder(httpTransport, jsonFactory, authenticator.getCredentials(httpTransport))
            .setApplicationName(APPLICATION_NAME)
            .build()

    fun getMessageIdList(query: String): List<StampMessageID> {
        val listResponse = gmail.users().messages().list(USER)
            .setQ(query)
            .execute()

        return listResponse.messages.mapNotNull { StampMessageID(it.id) }
            .also {
                Logger.log(LOG_TAG, "Got list of ${it.size} messages")
            }
    }

    fun getMessage(id: String): Message = gmail.users().messages().get(USER, id).execute()

    companion object {

        private const val LOG_TAG = "GmailDataSource"
        private const val APPLICATION_NAME = "Boilerplate"
        private const val USER = "me"
    }

}

