package com.clevertech.theosandbox.ui

data class UIChannelItem(
    val channelDetail: Channel,
    val currentProgramName: String?,
    val onAirTime: String,
    val isLive: Boolean = false,
    val pubnubChannelName: String,
    val isSelected: Boolean,
    val adTag: String?,
)
