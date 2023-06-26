package ru.salvadorvdali.webrtc_android_sample.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class UserDto (
    @SerializedName("username")
    val name: String
        )