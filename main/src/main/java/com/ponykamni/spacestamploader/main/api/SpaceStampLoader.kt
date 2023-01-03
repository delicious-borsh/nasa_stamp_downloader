package com.ponykamni.spacestamploader.main.api

import com.ponykamni.spacestamploader.mail.data.MailRepository
import com.ponykamni.spacestamploader.main.domain.GetStampsRecordsUseCase
import com.ponykamni.spacestamploader.main.domain.ProcessStampScenario
import com.ponykamni.spacestamploader.main.domain.ValidateStampUseCase
import com.ponykamni.spacestamploader.path.PathsProvider
import java.io.File
import javax.inject.Inject

class SpaceStampLoader @Inject constructor(
    private val processStampScenario: ProcessStampScenario,
    private val getStampsRecordsUseCase: GetStampsRecordsUseCase,
    private val gmailRepository: MailRepository,
    private val validateStampUseCase: ValidateStampUseCase,
) {

    suspend fun getStamps(): List<Stamp> {
        prepareFolder()
        val ids = gmailRepository.getMessages()

        for (id in ids) {
            processStampScenario(id)
        }

        return getStampsRecordsUseCase()
            .mapNotNull {
                validateStampUseCase(it)
            }
    }

    private fun prepareFolder() {
        val filesFolder = File(PathsProvider.getFilesFolderName())

        if (!filesFolder.exists()) {
            filesFolder.mkdir()
        }
    }
}