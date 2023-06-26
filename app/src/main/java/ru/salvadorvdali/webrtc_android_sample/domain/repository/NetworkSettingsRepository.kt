package ru.salvadorvdali.webrtc_android_sample.domain.repository

interface NetworkSettingsRepository {

    fun savePort(port: Int)

    fun getPort(): Int?

    fun getHost(): String

    fun setHost(address: String)

}