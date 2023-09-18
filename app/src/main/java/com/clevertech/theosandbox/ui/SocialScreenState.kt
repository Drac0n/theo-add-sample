package com.ballyscorp.ballylive.ui.social

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ballyscorp.ballylive.ui.common.components.video.player.BallysVideoPlayer
import com.ballyscorp.ballylive.ui.common.theme.BallysRippleTheme
import com.ballyscorp.ballylive.ui.common.theme.BallysTheme
import com.ballyscorp.ballylive.ui.social.channels.ChannelsTab
import com.clevertech.theosandbox.R
import com.clevertech.theosandbox.ui.UIChannelItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun SocialScreen(
    viewModel: SocialViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    SocialScreenContent(
        state = state,
        viewModel = viewModel,
    )
}

@Composable
private fun SocialScreenContent(
    state: SocialScreenState,
    viewModel: SocialViewModel, // TODO just a temporary solution, remove it from here
) {
    if (state is SocialScreenState.Data) {
        BallysRippleTheme {
            Column {
                when (val selectedChannel = state.selectedChannel) {
                    null -> {
                        Box(
                            Modifier
                                .height(233.dp)
                                .fillMaxWidth()
                                .background(Color.Black)
                        )
                    }

                    else -> {
                        BallysVideoPlayer(
                            uri = selectedChannel.channelDetail.streamUrl,
                            isLive = selectedChannel.isLive,
                            chatId = selectedChannel.pubnubChannelName,
                            adTag = selectedChannel.adTag,
                            modifier = Modifier
                                .height(233.dp)
                                .fillMaxWidth(),
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 16.dp, end = 12.dp)
                        ) {
                            Text(
                                text = selectedChannel.currentProgramName
                                    ?: stringResource(id = R.string.coming_up_next),
                                modifier = Modifier
                                    .background(BallysTheme.colors.terrain.white)
                                    .padding(vertical = 8.dp),
                                style = BallysTheme.typography.bodyBold,
                                color = BallysTheme.colors.terrain.hue.purple1,
                            )

                            Spacer(modifier = Modifier.weight(1f))

                        }

                    }
                }
                BallysSocialNavigation(
                    state = state,
                    viewModel,
                    onChannelSelected = viewModel::showNewStream
                )
            }
        }
    }
}

@Composable
fun BallysSocialNavigation(
    state: SocialScreenState.Data,
    viewModel: SocialViewModel,
    onChannelSelected: (UIChannelItem) -> Unit,
) {
    ChannelsTab(
        onChannelSelected = onChannelSelected
    )
}
