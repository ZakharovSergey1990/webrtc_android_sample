package ru.salvadorvdali.webrtc_android_sample.presentation.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class NavigationViewModel @Inject constructor() : ViewModel() {

    protected var navActions: NavigationActions? = null

    fun initNavActions(actions: NavigationActions) {
        navActions = actions
    }

    fun goBack(){
        navActions?.goBack()
    }
}