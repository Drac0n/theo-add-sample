package com.ballyscorp.ballylive.ui.common.components.video.player

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.clevertech.theosandbox.BuildConfig
import com.theoplayer.android.api.THEOplayerConfig
import com.theoplayer.android.api.ads.ima.GoogleImaAdErrorEvent
import com.theoplayer.android.api.ads.ima.GoogleImaAdEventType
import com.theoplayer.android.api.ads.ima.GoogleImaIntegrationFactory
import com.theoplayer.android.api.source.SourceDescription
import com.theoplayer.android.api.source.addescription.GoogleImaAdDescription
import com.theoplayer.android.ui.Player

@SuppressLint("UnsafeOptInUsageError")
@SuppressWarnings("UnusedPrivateMember")
@Composable
fun BallysVideoPlayer(
    modifier: Modifier = Modifier,
    uri: String,
    adTag: String? = null,
    isLive: Boolean,
    chatId: String,
) {
    val config = THEOplayerConfig.Builder()
        .license(BuildConfig.THEO_LICENSE)
        .build()
    val player = rememberPlayer(config)

    LaunchedEffect(player) {
        Log.e("Test", "Launched effet player")
        val googleImaIntegration = GoogleImaIntegrationFactory.createGoogleImaIntegration(player.theoplayerView!!)
        player.player?.addIntegration(googleImaIntegration)
        player.player?.ads?.addEventListener(GoogleImaAdEventType.AD_ERROR) {
            Log.e("Test", "Ad error = ${(it as GoogleImaAdErrorEvent).adError}")
        }
        player.player?.ads?.addEventListener(GoogleImaAdEventType.AD_BUFFERING) {
            Log.e("Test", "Ad buffering = ${it.ad?.id}")
        }
        player.player?.ads?.addEventListener(GoogleImaAdEventType.ALL_ADS_COMPLETED) {
            Log.e("Test", "All ads completed = ${it.ad?.id}")
        }
        player.player?.ads?.addEventListener(GoogleImaAdEventType.AD_PROGRESS) {
            Log.e("Test", "Ad progress = ${it.ad?.id}")
        }
        player.player?.ads?.addEventListener(GoogleImaAdEventType.STARTED) {
            Log.e("Test", "Ad started = ${it.ad?.id}")
        }
        player.player?.ads?.addEventListener(GoogleImaAdEventType.LOADED) {
            Log.e("Test", "Ad loaded = ${it.ad?.id}")
        }
    }



    val configuration = LocalConfiguration.current
    val isLandscapeMode = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    LaunchedEffect(player, uri) {
        Log.e("Test", "Launched effet player and uri")
//        val typedSource = TypedSource.Builder(uri).build()
        val imaAdDescription = adTag?.run {
            GoogleImaAdDescription.Builder("https://cdn.theoplayer.com/demos/ads/vast/dfp-preroll-no-skip.xml")
                .timeOffset("start")
                .build()
        }
        // Set up the source with the given uri and ad description
        val source = SourceDescription.Builder(uri)
            .ads(imaAdDescription)
            .build()
        player.source = source
        if (player.fullscreen != isLandscapeMode) {
            player.fullscreen = isLandscapeMode
        }
        player.play()
    }

    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    BallysVideoPlayerContent(
        player = player,
        modifier = modifier,
        lifecycle = lifecycle,
        isLive = isLive,
        chatId = chatId,
        isLandscapeMode = isLandscapeMode,
    )
}

@SuppressLint("UnsafeOptInUsageError")
@Composable
private fun BallysVideoPlayerContent(
    modifier: Modifier = Modifier,
    player: Player,
    lifecycle: Lifecycle.Event,
    isLive: Boolean,
    isLandscapeMode: Boolean,
    chatId: String,
) {
    when (lifecycle) {
        Lifecycle.Event.ON_PAUSE -> {
            player.pause()
        }

        Lifecycle.Event.ON_RESUME -> {
            player.play()
        }

        else -> Unit
    }

    val activity = (LocalContext.current as Activity)

    Box(modifier = modifier) {
        UIController(
            modifier = modifier,
            player = player,
        )
    }
}
