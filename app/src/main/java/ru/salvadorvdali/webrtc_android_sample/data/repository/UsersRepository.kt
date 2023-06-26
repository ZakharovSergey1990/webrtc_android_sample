package ru.salvadorvdali.webrtc_android_sample.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.salvadorvdali.webrtc_android_sample.domain.repository.UsersRepository
import javax.inject.Inject




class UsersRepositoryImpl @Inject constructor(): UsersRepository {

    private var userName: String? = null

    private val users = MutableStateFlow(listOf<String>())

    override fun setUserName(name: String){
        userName = name
    }

    override fun getUserName(): String? {
        return userName
    }

    override fun clearUserName() {
        userName = null
    }

    override fun getUsersList(): Flow<List<String>> {
        return users
    }

    override fun saveUsersList(users: List<String>){
        this.users.value = users
    }
}