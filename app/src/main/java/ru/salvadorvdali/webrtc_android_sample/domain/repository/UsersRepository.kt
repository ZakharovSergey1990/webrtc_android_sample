package ru.salvadorvdali.webrtc_android_sample.domain.repository

import kotlinx.coroutines.flow.Flow


interface UsersRepository {

    fun setUserName(name: String)

    fun getUserName(): String?

    fun clearUserName()

    fun getUsersList(): Flow<List<String>>

    fun saveUsersList(users: List<String>)
}