package ru.salvadorvdali.webrtc_android_sample.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.salvadorvdali.webrtc_android_sample.data.network.WebSocketService
import ru.salvadorvdali.webrtc_android_sample.data.network.WebSocketServiceImpl
import ru.salvadorvdali.webrtc_android_sample.data.repository.UsersRepositoryImpl
import ru.salvadorvdali.webrtc_android_sample.domain.repository.UsersRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Singleton
    @Binds
    abstract fun bindWebSocketService(value: WebSocketServiceImpl): WebSocketService

    @Singleton
    @Binds
    abstract fun bindUsersRepository(value: UsersRepositoryImpl): UsersRepository
}