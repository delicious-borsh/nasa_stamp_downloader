package data

import java.net.URI

data class Stamp(
    val messageID: MessageID?,
    val messageSubject: String,
    val stampUrl: URI,
)