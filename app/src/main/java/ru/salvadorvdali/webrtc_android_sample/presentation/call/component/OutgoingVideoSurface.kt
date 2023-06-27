package ru.salvadorvdali.webrtc_android_sample.presentation.call.component

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import org.webrtc.SurfaceViewRenderer

@Composable
fun OutgoingVideoSurface(localVideo: (SurfaceViewRenderer) -> Unit, modifier: Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        AndroidView(modifier = Modifier
            .width(100.dp)
            .height(170.dp),
            factory = { context: Context ->
                val surface = SurfaceViewRenderer(context)
                surface.setZOrderOnTop(true)
                localVideo(surface)
                surface
            },
            update = { view -> })
    }
}