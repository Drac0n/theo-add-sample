package com.ballyscorp.ballylive.ui.social.channels

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ballyscorp.ballylive.ui.social.channels.state.ChannelsState
import com.clevertech.theosandbox.LoadingEvent
import com.clevertech.theosandbox.ui.UIChannelItem

@Composable
fun ChannelsTab(
    viewModel: ChannelsViewModel = hiltViewModel(),
    onChannelSelected: (UIChannelItem) -> Unit,
) {
    val state by viewModel.state.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        when (state) {
            is ChannelsState.ChannelList -> {
                // FIXME just a hacky way to provide selected channel to SocialViewModel, must be refactored
                ((state).channelList as? LoadingEvent.Success)?.data?.find { it.isSelected }?.let {
                    onChannelSelected(it)
                }

                ChannelListScreen(
                    state = state as ChannelsState.ChannelList,
                    modifier = Modifier.fillMaxSize(),
                    onChannelClick = { viewModel.selectChannel(it) },
                )
            }

        }
    }
}
