package ru.salvadorvdali.webrtc_android_sample.presentation.users

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.salvadorvdali.webrtc_android_sample.presentation.navigation.NavigationViewModel
import javax.inject.Inject


@HiltViewModel
class UsersPageViewModel @Inject constructor(): NavigationViewModel() {

    private val _users = MutableStateFlow<List<String>>(emptyList())
    val users : StateFlow<List<String>> = _users

    fun onUserClick(user: String){

    }

}