package ru.salvadorvdali.webrtc_android_sample.presentation.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersPage(vm: UsersPageViewModel) {
    val users by vm.users.collectAsState()
    Scaffold(
        topBar = { Text("Users") }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(users) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        vm.onUserClick(it)
                    }, horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = it , modifier = Modifier.padding(16.dp))
                    Icon(imageVector = Icons.Filled.Call , modifier = Modifier.padding(16.dp), contentDescription = null)
                }
            }
        }
    }
}