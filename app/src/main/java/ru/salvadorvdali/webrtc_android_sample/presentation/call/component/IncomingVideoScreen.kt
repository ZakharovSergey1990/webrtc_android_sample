package ru.salvadorvdali.webrtc_android_sample.presentation.call.component

import android.content.Context
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.flow.Flow
import org.webrtc.RendererCommon
import org.webrtc.SurfaceViewRenderer

@Composable
fun IncomingVideoScreen(
    remoteVideo: (SurfaceViewRenderer) -> Unit,
    modifier: Modifier
) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var boxSize by remember { mutableStateOf<IntSize>(IntSize(0, 0)) }
    var surfaceSize by remember { mutableStateOf<IntSize>(IntSize(0, 0)) }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
       Box(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, rotation ->
                    val newScale = scale * zoom
                    if (newScale < 1) {
                        scale = 1f
                        offsetY = 0f
                        offsetX = 0f
                        return@detectTransformGestures
                    }

                    scale = if (newScale > 3) 3f else newScale

                    val newSurfaceSize = IntSize(
                        (surfaceSize.width * scale).toInt(), (surfaceSize.height * scale).toInt()
                    )
                    val maxOffsetX = (newSurfaceSize.width - boxSize.width) / 2f
                    val maxOffsetY = (newSurfaceSize.height - boxSize.height) / 2f
                    val newOffsetX = offsetX + pan.x
                    val newOffsetY = offsetY + pan.y

                    offsetX = when {
                        boxSize.width >= newSurfaceSize.width -> offsetX
                        newOffsetX > maxOffsetX -> maxOffsetX
                        newOffsetX < -maxOffsetX -> -maxOffsetX
                        else -> newOffsetX
                    }
                    offsetY = when {
                        boxSize.height >= newSurfaceSize.height -> offsetY
                        newOffsetY > maxOffsetY -> maxOffsetY
                        newOffsetY < -maxOffsetY -> -maxOffsetY
                        else -> newOffsetY
                    }
                }
            }
            .onSizeChanged {
                boxSize = it
            }, contentAlignment = Alignment.Center) {
            AndroidView(modifier = Modifier
                .graphicsLayer(
                    scaleX = scale, scaleY = scale, translationX = offsetX, translationY = offsetY
                )
                .onSizeChanged {
                    surfaceSize = it
                }
                .fillMaxWidth(),
                factory = { context: Context ->
                    val surface = SurfaceViewRenderer(context)
                    surface.setZOrderOnTop(false)
                    surface.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_FIT)
                    remoteVideo(surface)
                    surface
                }, update = { view -> })
        }
    }
}