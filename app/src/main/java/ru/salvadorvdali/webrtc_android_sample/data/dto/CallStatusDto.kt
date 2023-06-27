package ru.salvadorvdali.webrtc_android_sample.data.dto

import com.google.gson.annotations.SerializedName

enum class CallStatusDto{
    @SerializedName("INCOMING")
    INCOMING,
    @SerializedName("UPDATE")
    UPDATE,
    @SerializedName("TERMINATE")
    TERMINATE
}

