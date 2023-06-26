package ru.salvadorvdali.webrtc_android_sample.presentation.navigation

import androidx.navigation.NavController

class NavigationActions (val navController: NavController) {

    fun goToMembersList(){
        navController.navigate(MEMBERS_PAGE){

        }
    }

    fun goBack(){
        navController.popBackStack()
    }

   companion object{
       const val LOGIN_PAGE = "/login"
       const val MEMBERS_PAGE = "/members"
       const val CALL_PAGE = "/call/{name}"
   }
}