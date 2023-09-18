package com.ballyscorp.ballylive.ui.social.channels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ballyscorp.ballylive.functionality.shared.navigation.AppNavigator
import com.ballyscorp.ballylive.functionality.shared.navigation.NavTarget
import com.ballyscorp.ballylive.ui.social.channels.state.ChannelsState
import com.clevertech.theosandbox.LoadingEvent
import com.clevertech.theosandbox.ui.Channel
import com.clevertech.theosandbox.ui.UIChannelItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ChannelsViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    private val selectedChannelToDisplay = MutableStateFlow<Channel?>(null)

    private val channelList: Flow<LoadingEvent<List<UIChannelItem>>> =
        selectedChannelToDisplay.map {selectedChannel ->
            val channelList = mockChannels()

            LoadingEvent.Success(
                data = mapScheduleList(
                    channelList,
                    selectedChannel
                )
            )
    }

    val state = combine(
        channelList,
        selectedChannelToDisplay
    ) { channelList, selectedChannelToDisplay ->
            ChannelsState.ChannelList(
                selectedChannelToDisplay = selectedChannelToDisplay,
                channelList = channelList,
            )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = ChannelsState.ChannelList(
            selectedChannelToDisplay = null,
            channelList = LoadingEvent.Loading
        )
    )

    private fun mapScheduleList(
        channelList: List<Channel>,
        selectedChannel: Channel?
    ): List<UIChannelItem> {
        return channelList.map { channel ->
            UIChannelItem(
                channelDetail = channel,
                onAirTime = "",
                isLive = false,
                isSelected = channel.uuid == selectedChannel?.uuid,
                pubnubChannelName = channel.pubnubChannelName,
                currentProgramName = "Testing",
                adTag = channel.adPreRollTag,
            )
        }
    }

    fun selectChannel(channel: UIChannelItem) {
        selectedChannelToDisplay.update { channel.channelDetail }
    }
    private fun mockChannels() : List<Channel> {
        val list = mutableListOf<Channel>()
        list.add(
            Channel(
                "https://stadium1.channels.ballys.tv/origin/stadium/smil:linear15.smil/playlist.m3u8",
                "https://assets-stratosphere.ballys.tv/images/channel_icons/stadium/Stadium.png",
                "Stadium",
                1,
                15,
                "public.15",
                "https://pubads.g.doubleclick.net/gampad/ads?iu=/21775744923/external/vmap_ad_samples&sz=640x480&cust_params=sample_ar%3Dpreonly&ciu_szs=300x250%2C728x90&gdfp_req=1&ad_rule=1&output=vmap&unviewed_position_start=1&env=vp&impl=s&correlator=",
                null
            )
        )
        list.add(
            Channel(
                "https://milb1.channels.ballys.tv/origin/milb/smil:linear1.smil/playlist.m3u8",
                "https://assets-stratosphere.ballys.tv/images/channel_icons/stadium/Stadium.png",
                "Stadium",
                2,
                14,
                "public.15",
                "https://pubads.g.doubleclick.net/gampad/ads?iu=/21775744923/external/vmap_ad_samples&sz=640x480&cust_params=sample_ar%3Dpreonly&ciu_szs=300x250%2C728x90&gdfp_req=1&ad_rule=1&output=vmap&unviewed_position_start=1&env=vp&impl=s&correlator=",
                null
            )
        )
        list.add(
            Channel(
                "https://milb2.channels.ballys.tv/origin/milb/smil:linear2.smil/playlist.m3u8",
                "https://assets-stratosphere.ballys.tv/images/channel_icons/stadium/Stadium.png",
                "Stadium",
                3,
                13,
                "public.15",
                "https://pubads.g.doubleclick.net/gampad/ads?iu=/21775744923/external/vmap_ad_samples&sz=640x480&cust_params=sample_ar%3Dpreonly&ciu_szs=300x250%2C728x90&gdfp_req=1&ad_rule=1&output=vmap&unviewed_position_start=1&env=vp&impl=s&correlator=",
                null
            )
        )
        list.add(
            Channel(
                "https://milb3.channels.ballys.tv/origin/milb/smil:linear3.smil/playlist.m3u8",
                "https://assets-stratosphere.ballys.tv/images/channel_icons/stadium/Stadium.png",
                "Stadium",
                4,
                12,
                "public.15",
                "https://pubads.g.doubleclick.net/gampad/ads?iu=/21775744923/external/vmap_ad_samples&sz=640x480&cust_params=sample_ar%3Dpreonly&ciu_szs=300x250%2C728x90&gdfp_req=1&ad_rule=1&output=vmap&unviewed_position_start=1&env=vp&impl=s&correlator=",
                null
            )
        )
        list.add(
            Channel(
                "https://milb4.channels.ballys.tv/origin/milb/smil:linear4.smil/playlist.m3u8",
                "https://assets-stratosphere.ballys.tv/images/channel_icons/stadium/Stadium.png",
                "Stadium",
                5,
                11,
                "public.15",
                "https://pubads.g.doubleclick.net/gampad/ads?iu=/21775744923/external/vmap_ad_samples&sz=640x480&cust_params=sample_ar%3Dpreonly&ciu_szs=300x250%2C728x90&gdfp_req=1&ad_rule=1&output=vmap&unviewed_position_start=1&env=vp&impl=s&correlator=",
                null
            )
        )
        return list
    }
}
