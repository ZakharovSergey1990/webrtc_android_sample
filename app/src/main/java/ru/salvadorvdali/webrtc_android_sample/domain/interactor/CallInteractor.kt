package ru.salvadorvdali.webrtc_android_sample.domain.interactor

interface CallInteractor {
    fun terminateCall(name: String)
    fun answerCall(name: String)
    fun startCall(name: String)
}