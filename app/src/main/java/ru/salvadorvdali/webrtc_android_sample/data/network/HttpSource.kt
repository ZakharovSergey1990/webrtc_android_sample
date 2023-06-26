package ru.salvadorvdali.webrtc_android_sample.data.network

import android.util.Log
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.*
import io.ktor.serialization.gson.*
import ru.salvadorvdali.webrtc_android_sample.data.dto.UserDto
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface HttpSource {
    suspend fun login(name: String): Boolean
}


class HttpSourceImpl  constructor(): HttpSource{

    val TAG = this::class.java.simpleName

    val client = HttpClient(Android){

        install(ContentNegotiation) {
            gson()
        }

        install(HttpTimeout) {
            requestTimeoutMillis = TimeUnit.SECONDS.toMillis(20)
            socketTimeoutMillis = TimeUnit.SECONDS.toMillis(20)
            connectTimeoutMillis = TimeUnit.SECONDS.toMillis(20)
        }

    }


    override suspend fun login(name: String): Boolean {
        val response: HttpResponse = client.post("http://10.0.2.2:8080/login") {
            contentType(ContentType.Application.Json)
            setBody(UserDto(name))
        }
        Log.i(TAG, "response = $response")
        return false
    }
}