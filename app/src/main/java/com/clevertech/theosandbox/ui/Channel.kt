package com.clevertech.theosandbox.ui

data class Channel(
    val streamUrl: String,
    val logo: String,
    val name: String,
    val order: Int?,
    val uuid: Int,
    val pubnubChannelName: String,
    val adPreRollTag: String?,
    val adMidRollTag: String?,
)
