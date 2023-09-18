package com.ballyscorp.ballylive.ui.social

import com.clevertech.theosandbox.ui.UIChannelItem

sealed interface SocialScreenState {

    sealed interface Initial : SocialScreenState {
        object Loading : Initial
    }

    data class Data(
        val selectedChannel: UIChannelItem? = null
    ) : SocialScreenState
}
