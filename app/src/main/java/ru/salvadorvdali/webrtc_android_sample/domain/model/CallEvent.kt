package ru.salvadorvdali.webrtc_android_sample.domain.model

data class CallEvent(
    val callEventDescription: CallEventDescription?,
    val status: CallStatus,
)
