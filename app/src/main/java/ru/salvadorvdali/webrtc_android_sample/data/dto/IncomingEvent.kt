package ru.salvadorvdali.webrtc_android_sample.data.dto

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class IncomingEvent(
    @SerializedName("from_user")
    val fromUser: String,
    @SerializedName("body")
    val body: JsonObject
)