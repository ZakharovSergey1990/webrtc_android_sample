package ru.salvadorvdali.webrtc_android_sample.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializer
import ru.salvadorvdali.webrtc_android_sample.domain.model.CallEventDescription
import ru.salvadorvdali.webrtc_android_sample.domain.model.CallStatus

data class CallEventDto(
    @SerializedName("description")
    val callEventDescription: CallEventDescription?,
    @SerializedName("status")
    val status: CallStatus,
)
