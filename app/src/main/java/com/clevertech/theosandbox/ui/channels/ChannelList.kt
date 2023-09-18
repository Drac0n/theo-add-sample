package com.ballyscorp.ballylive.ui.social.channels

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ballyscorp.ballylive.ui.common.theme.BallysTheme
import com.ballyscorp.ballylive.ui.social.channels.composable.ChannelItem
import com.ballyscorp.ballylive.ui.social.channels.state.ChannelsState
import com.clevertech.theosandbox.LoadingEvent
import com.clevertech.theosandbox.ui.UIChannelItem

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun ChannelListScreen(
    modifier: Modifier = Modifier,
    state: ChannelsState.ChannelList,
    onChannelClick: (UIChannelItem) -> Unit,
) {
        if (state.channelList is LoadingEvent.Success) {
            // FIXME - Just a workaround to start a video stream from a first channel,
            //  it needs to be refactored.
            if (state.selectedChannelToDisplay == null) {
                LaunchedEffect(Unit) {
                    state.channelList.data.firstOrNull()?.let {
                        onChannelClick(it)
                    }
                }
            }
            val listState = rememberLazyListState()
            val indexOfSelected = state.channelList.data.indexOfFirst { it.isSelected }

            LaunchedEffect(key1 = Unit) {
                if (indexOfSelected >= 0) {
                    listState.animateScrollToItem(indexOfSelected)
                }
            }
            LazyColumn(
                modifier = modifier,
                state = listState,
            ) {
                items(
                    items = state.channelList.data,
                    key = { item -> item.channelDetail.uuid }
                ) { item ->
                    ChannelItem(
                        item = item,
                        onClick = onChannelClick,
                    )
                    Divider(thickness = 1.dp, color = BallysTheme.colors.terrain.hue.purple5)
                }
            }

        }
}
