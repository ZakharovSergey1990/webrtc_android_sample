package ru.salvadorvdali.webrtc_android_sample.domain.model

sealed class Resource <out T> {
    object Loading : Resource <Nothing> ()
    data class Success <T> ( val result: T ): Resource<T>()
    data class Error(val errorMessage: String?): Resource <Nothing>()
}