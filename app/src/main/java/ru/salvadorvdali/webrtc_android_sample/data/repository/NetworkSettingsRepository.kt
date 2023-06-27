package ru.salvadorvdali.webrtc_android_sample.data.repository

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.salvadorvdali.webrtc_android_sample.domain.repository.NetworkSettingsRepository
import javax.inject.Inject

class NetworkSettingsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): NetworkSettingsRepository {

    private var sharedPreferences: SharedPreferences = context.getSharedPreferences( "NETWORK_SETTINGS", Context.MODE_PRIVATE)

    override fun savePort(port: Int) {
        sharedPreferences.edit().putInt(PORT, port).apply()
    }

    override fun getPort(): Int {
        return sharedPreferences.getInt(PORT, 8080)
    }

    override fun getHost(): String {
        return sharedPreferences.getString(HOST, "10.0.2.2").toString()
    }

    override fun setHost(address: String) {
        sharedPreferences.edit().putString(HOST, address).apply()
    }

    companion object {
        const val PORT = "PORT"
        const val HOST = "HOST"
    }
}