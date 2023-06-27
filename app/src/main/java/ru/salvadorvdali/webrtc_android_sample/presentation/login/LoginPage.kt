package ru.salvadorvdali.webrtc_android_sample.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import ru.salvadorvdali.webrtc_android_sample.domain.model.NetworkSettings

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginPage(vm: LoginViewModel = hiltViewModel()) {

    var showDialog by remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
            IconButton(onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
            }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {

                var loginText by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = loginText,
                    onValueChange = { loginText = it },
                    singleLine = true,
                    label = { Text(text = "Name") },
                    supportingText = { Text(text = "Enter your name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    // isError = true
                )
                Button(
                    onClick = { vm.login(loginText) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("LOGIN")
                }
            }
        }

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Surface {
                    Column {
                        var addressText by remember { mutableStateOf(vm.settings.host) }
                        var portText by remember { mutableStateOf(vm.settings.port.toString()) }
                        OutlinedTextField(
                            value = addressText,
                            onValueChange = { addressText = it },
                            singleLine = true,
                            label = { Text(text = "address") },
                            supportingText = { Text(text = "Enter address") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                        )
                        OutlinedTextField(
                            value = portText,
                            onValueChange = { portText = it },
                            singleLine = true,
                            label = { Text(text = "port") },
                            supportingText = { Text(text = "Enter port") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                        )
                        Row (Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                            TextButton(onClick = { showDialog = false }) {
                                Text("CANCEL")
                            }
                            TextButton(onClick = {
                                vm.saveNetworkSettings(NetworkSettings(addressText, portText.toInt()))
                                showDialog = false
                            }) {
                                Text("SAVE")
                            }
                        }
                    }
                }
            }
        }
    }
}