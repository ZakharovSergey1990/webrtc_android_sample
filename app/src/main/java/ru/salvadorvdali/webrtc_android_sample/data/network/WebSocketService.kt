package ru.salvadorvdali.webrtc_android_sample.data.network

import android.util.Log
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.network.sockets.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import ru.salvadorvdali.webrtc_android_sample.data.dto.OutgoingEvent
import java.net.ConnectException
import java.util.concurrent.CancellationException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface WebSocketService {
    fun connect(username: String, address: String, port: Int)
}

class WebSocketServiceImpl @Inject constructor(): WebSocketService {

    private val TAG = this::class.java.simpleName

    private val scope = CoroutineScope(Dispatchers.IO)

    private val incomingMessages = MutableSharedFlow<String>(replay = 1, extraBufferCapacity = 5)

    private val gson = Gson()

    private var send: (suspend (String) -> Unit)? = null

    private val httpClient = HttpClient {

        install(HttpTimeout) {
            requestTimeoutMillis = TimeUnit.SECONDS.toMillis(20)
            socketTimeoutMillis = TimeUnit.SECONDS.toMillis(20)
            connectTimeoutMillis = TimeUnit.SECONDS.toMillis(20)
        }

        install(WebSockets)

        install(ContentNegotiation) {
            gson()
        }
    }

    private var currentWebSocketSession: DefaultClientWebSocketSession? = null


    override fun connect(username: String, address: String, port: Int) {
        Log.i(TAG, "connect: $username, address = $address")
        CoroutineScope(Dispatchers.IO).launch {


            try {
                httpClient.webSocket(HttpMethod.Get, address,port, "ws/$username") {

                    Log.i(TAG, "connect: $this")

                    currentWebSocketSession = this

                    send = { message: String -> this.send(message) }

                    receiveMessage(incoming)
                }
            } catch (e: ConnectException) {
                Log.e(TAG, "connect: No internet connection", e)
            } catch (e: SocketTimeoutException) {
                Log.e(TAG, "connect: Socket timeout", e)
            } catch (e: NoTransformationFoundException) {
                Log.e(TAG, "connect: No transformation found", e)
            } catch (e: Exception) {
                Log.e(TAG, "connect:", e)
            }
        }
    }

    suspend fun sent(event: OutgoingEvent) {
        send?.invoke(gson.toJson(event))
    }

    fun events(): Flow<String> = incomingMessages

    private suspend fun receiveMessage(incoming: ReceiveChannel<Frame>) {
        try {
            incoming.consumeEach { frame ->
                if (frame is Frame.Text) {
                    val text = frame.readText()
                    incomingMessages.emit(text)
                    Log.i(TAG, "handleMessage: text = $text")
                }
            }
        } catch (e: ClosedReceiveChannelException) {
            Log.e(TAG, "Connection lost (ClosedReceiveChannelException)", e)
        } catch (e: CancellationException) {
            Log.e(TAG, "Connection lost (CancellationException)", e)
        } catch (e: Exception) {
            Log.e(TAG, "Error while processing incoming messages:", e)
        } finally {
            Log.e(TAG, "handleMessage: finally")
        }
    }
}