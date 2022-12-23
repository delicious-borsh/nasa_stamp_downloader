package data.gmail

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.gmail.GmailScopes
import java.io.*

class GoogleCloudAuthenticator(
    private val jsonFactory: JsonFactory,
) {

    @Throws(IOException::class)
    fun getCredentials(httpTransport: NetHttpTransport): Credential {
        val clientSecrets: GoogleClientSecrets = getSecrets()

        val flow = getAuthorizationFlow(clientSecrets, httpTransport)
        val receiver = getLocalServerReceiver()

        return AuthorizationCodeInstalledApp(flow, receiver)
            .authorize("user")
    }

    private fun getSecrets(): GoogleClientSecrets {
        val file = File(CREDENTIALS_FILE_PATH)
        if (!file.exists()) {
            throw FileNotFoundException("File not found: $CREDENTIALS_FILE_PATH")
        }

        return GoogleClientSecrets.load(jsonFactory, file.reader())
    }

    private fun getAuthorizationFlow(
        clientSecrets: GoogleClientSecrets,
        httpTransport: NetHttpTransport,
    ): GoogleAuthorizationCodeFlow =
        GoogleAuthorizationCodeFlow
            .Builder(
                httpTransport,
                jsonFactory,
                clientSecrets,
                SCOPES
            )
            .setDataStoreFactory(
                FileDataStoreFactory(File(TOKENS_DIRECTORY_PATH))
            )
            .setAccessType("offline")
            .build()

    private fun getLocalServerReceiver(): LocalServerReceiver =
        LocalServerReceiver
            .Builder()
            .setPort(8888)
            .build()

    companion object {

        private const val TOKENS_DIRECTORY_PATH = "tokens"

        private val SCOPES = listOf(GmailScopes.GMAIL_LABELS, GmailScopes.GMAIL_READONLY)
        private const val CREDENTIALS_FILE_PATH = "credentials.json"
    }
}