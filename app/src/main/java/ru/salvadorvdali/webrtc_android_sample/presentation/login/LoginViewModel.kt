package ru.salvadorvdali.webrtc_android_sample.presentation.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.salvadorvdali.webrtc_android_sample.domain.interactor.NetworkSettingsInteractor
import ru.salvadorvdali.webrtc_android_sample.domain.model.NetworkSettings
import ru.salvadorvdali.webrtc_android_sample.domain.model.Resource
import ru.salvadorvdali.webrtc_android_sample.domain.usecase.InitConnectionUseCase
import ru.salvadorvdali.webrtc_android_sample.presentation.navigation.NavigationViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val initConnectionUseCase: InitConnectionUseCase,
    private val networkSettingsInteractor: NetworkSettingsInteractor
): NavigationViewModel() {

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    var settings: NetworkSettings = networkSettingsInteractor.getSettings()
    private set

    fun login(name: String){
        initConnectionUseCase.invoke(name, settings.host, settings.port).onEach {result ->
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

    fun saveNetworkSettings(settings: NetworkSettings){
        networkSettingsInteractor.saveSettings(settings)
        this.settings = settings
    }
}