package ru.salvadorvdali.webrtc_android_sample.presentation.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.salvadorvdali.webrtc_android_sample.domain.model.Resource
import ru.salvadorvdali.webrtc_android_sample.domain.usecase.InitConnectionUseCase
import ru.salvadorvdali.webrtc_android_sample.presentation.navigation.NavigationViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
private val initConnectionUseCase: InitConnectionUseCase
): NavigationViewModel() {

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun login(name: String, url: String){
        initConnectionUseCase.invoke(name, url).onEach {result ->
            when(result){
                is Resource.Success -> {
                    navActions?.goToMembersList()
                }
                 is Resource.Loading -> {

                 }
                 is Resource.Error -> {

                 }

            }
        }.launchIn( viewModelScope )
    }
}