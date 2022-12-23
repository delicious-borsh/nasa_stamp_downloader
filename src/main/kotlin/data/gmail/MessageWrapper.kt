package data.gmail

import com.google.api.services.gmail.model.Message
import data.entity.StampMessageID

class MessageWrapper(
    private val message: Message,
) {

    private val plainBody: String? by lazy {
        message.payload.parts?.get(1)?.body?.decodeData()?.let {
            String(it)
        }
    }

    fun getSubject(): String? =
        message
            .payload
            .headers
            .find {
                it["name"] == "Subject"
            }
            ?.get("value")?.toString()

    fun getId(): StampMessageID? = message.id?.let { StampMessageID(it) }

    fun getBody(): String? = plainBody
}