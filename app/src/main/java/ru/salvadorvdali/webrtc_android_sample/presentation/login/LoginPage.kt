package ru.salvadorvdali.webrtc_android_sample.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginPage(vm: LoginViewModel = hiltViewModel()) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {

                var loginText by remember { mutableStateOf("") }
                var addressText by remember { mutableStateOf("") }

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

                OutlinedTextField(
                    value = addressText,
                    onValueChange = { addressText = it },
                    singleLine = true,
                    label = { Text(text = "address") },
                    supportingText = { Text(text = "Enter address") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    // isError = true
                )

                Button(
                    onClick = { vm.login(loginText, addressText) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("LOGIN")
                }
            }
        }
    }
}