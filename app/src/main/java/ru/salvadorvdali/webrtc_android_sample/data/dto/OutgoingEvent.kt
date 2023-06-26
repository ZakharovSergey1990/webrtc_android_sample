package ru.salvadorvdali.webrtc_android_sample.data.dto

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class OutgoingEvent(
    @SerializedName("to_user")
    val toUser: String,
    @SerializedName("body")
    val body: JsonObject
)
