package ru.salvadorvdali.webrtc_android_sample.domain.interactor

import ru.salvadorvdali.webrtc_android_sample.domain.model.NetworkSettings
import ru.salvadorvdali.webrtc_android_sample.domain.repository.NetworkSettingsRepository
import javax.inject.Inject

interface NetworkSettingsInteractor{
    fun getSettings(): NetworkSettings
    fun saveSettings(settings: NetworkSettings)
}


class NetworkSettingsInteractorImpl @Inject constructor(
    private val networkSettingsRepository: NetworkSettingsRepository
): NetworkSettingsInteractor {
    override fun getSettings(): NetworkSettings {
        val port: Int = networkSettingsRepository.getPort()
        val host: String = networkSettingsRepository.getHost()
        return NetworkSettings(host, port)
    }

    override fun saveSettings(settings: NetworkSettings) {
        settings.port?.let { networkSettingsRepository.savePort(it) }
        networkSettingsRepository.setHost(settings.host)
    }
}