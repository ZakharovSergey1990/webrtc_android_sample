package ru.salvadorvdali.webrtc_android_sample.presentation.call

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import ru.salvadorvdali.webrtc_android_sample.presentation.call.component.IncomingVideoScreen
import ru.salvadorvdali.webrtc_android_sample.presentation.call.component.OutgoingVideoSurface

@Composable
fun CallPage(vm: CallPageViewModel) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (conference, timer, bottomBar, rotateIcon, action, backIcon, localVideo) = createRefs()
        val smallPaddings = 8.dp
        IncomingVideoScreen(remoteVideo = {}, modifier = Modifier
            .constrainAs(conference) {
                top.linkTo(parent.top, smallPaddings)
                bottom.linkTo(parent.bottom, smallPaddings)
                start.linkTo(parent.start, smallPaddings)
                end.linkTo(parent.end, smallPaddings)
            }
        )
        OutgoingVideoSurface(
            localVideo = {},
            modifier = Modifier
                .constrainAs(localVideo) {
                    bottom.linkTo(bottomBar.top, smallPaddings)
                    end.linkTo(parent.end, smallPaddings)
                })

        Button(
            modifier = Modifier
                .constrainAs(bottomBar) {
                    bottom.linkTo(parent.bottom, smallPaddings)
                    start.linkTo(parent.start, smallPaddings)
                    end.linkTo(parent.end, smallPaddings)
                }
                .padding(16.dp),
            onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.CallEnd, contentDescription = null)
        }
    }
}