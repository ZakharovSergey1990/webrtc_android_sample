package ru.salvadorvdali.webrtc_android_sample.presentation.call

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.salvadorvdali.webrtc_android_sample.domain.interactor.CallInteractor
import ru.salvadorvdali.webrtc_android_sample.presentation.navigation.NavigationViewModel
import javax.inject.Inject

@HiltViewModel
class CallPageViewModel @Inject constructor(
private val callInteractor: CallInteractor
): NavigationViewModel() {

    fun initCallPage(name: String){

    }

    fun terminateCall(){

    }
}