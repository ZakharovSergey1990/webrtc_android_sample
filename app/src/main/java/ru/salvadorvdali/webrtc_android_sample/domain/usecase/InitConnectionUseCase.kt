package ru.salvadorvdali.webrtc_android_sample.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import ru.salvadorvdali.webrtc_android_sample.data.network.WebSocketService
import ru.salvadorvdali.webrtc_android_sample.domain.model.Resource
import javax.inject.Inject

class InitConnectionUseCase @Inject constructor(
    private val webSocketService: WebSocketService
) {
        fun invoke(name: String, address: String, port: Int): Flow<Resource<Unit>> {
            return flow {
                emit(Resource.Loading)
                webSocketService.connect(name, address, port)
            }
        }
}