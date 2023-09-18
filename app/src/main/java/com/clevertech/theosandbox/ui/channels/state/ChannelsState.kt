package com.ballyscorp.ballylive.ui.social.channels.state

import com.clevertech.theosandbox.LoadingEvent
import com.clevertech.theosandbox.ui.Channel
import com.clevertech.theosandbox.ui.UIChannelItem

sealed class ChannelsState(
    open val selectedChannelToDisplay: Channel?,
) {
    data class ChannelList(
        override val selectedChannelToDisplay: Channel?,
        val channelList: LoadingEvent<List<UIChannelItem>>
    ) : ChannelsState(selectedChannelToDisplay)

}
