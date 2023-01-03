package com.ponykamni.spacestamploader.mail.data

import com.ponykamni.spacestamploader.entity.StampDto
import com.ponykamni.spacestamploader.entity.StampMessageID

interface MailRepository {

    fun getMessages(): List<StampMessageID>
    fun getRemoteMessage(stampMessageID: StampMessageID): StampDto?
}