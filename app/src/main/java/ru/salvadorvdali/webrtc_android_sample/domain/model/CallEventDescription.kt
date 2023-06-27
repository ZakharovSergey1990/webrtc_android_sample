package ru.salvadorvdali.webrtc_android_sample.domain.model

data class CallEventDescription(
   val isOfferSdp: Boolean,
   val sdp: String?
)
