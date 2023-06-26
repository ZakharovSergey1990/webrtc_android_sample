package ru.salvadorvdali.webrtc_android_sample.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.salvadorvdali.webrtc_android_sample.data.network.HttpSourceImpl
import ru.salvadorvdali.webrtc_android_sample.data.network.WebSocketServiceImpl
import ru.salvadorvdali.webrtc_android_sample.presentation.login.LoginPage
import ru.salvadorvdali.webrtc_android_sample.presentation.login.LoginViewModel
import ru.salvadorvdali.webrtc_android_sample.presentation.navigation.NavigationActions
import ru.salvadorvdali.webrtc_android_sample.presentation.theme.Webrtc_android_sampleTheme
import ru.salvadorvdali.webrtc_android_sample.presentation.users.UsersPage
import ru.salvadorvdali.webrtc_android_sample.presentation.users.UsersPageViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "onCreate")
        setContent {
            Webrtc_android_sampleTheme {
                val navController = rememberNavController()
                val actions = NavigationActions(navController)
                Scaffold {
                    Log.i("MainActivity", "Scaffold")
                    NavHost(navController = navController , startDestination = NavigationActions.LOGIN_PAGE){
                        Log.i("MainActivity", "NavHost")
                        composable(NavigationActions.LOGIN_PAGE){
                            val vm = hiltViewModel<LoginViewModel>().apply { initNavActions(actions) }
                            Log.i("MainActivity", "NavigationActions.LOGIN_PAGE")
                            LoginPage(vm = vm)
                        }
                        composable( NavigationActions.MEMBERS_PAGE ){
                            Log.i("MainActivity", "NavigationActions.MEMBERS_PAGE")
                            val vm = hiltViewModel<UsersPageViewModel>().apply { initNavActions(actions) }
                            UsersPage(vm = vm)
                        }
                    }
                }
            }
        }
    }
}






@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Webrtc_android_sampleTheme {
        Greeting("Android")
    }
}


