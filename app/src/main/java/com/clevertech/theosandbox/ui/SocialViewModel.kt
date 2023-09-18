package com.ballyscorp.ballylive.ui.social

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ballyscorp.ballylive.functionality.shared.navigation.AppNavigator
import com.clevertech.theosandbox.ui.UIChannelItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SocialViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
) : ViewModel() {

    private val selectedChannel = MutableStateFlow<UIChannelItem?>(null)

    val state =
        selectedChannel
            .map { SocialScreenState.Data(it) }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            SocialScreenState.Initial.Loading
    )

    fun showNewStream(channel: UIChannelItem) {
        Log.e("Test", "Selected new stream = $channel")
        selectedChannel.update { channel }
    }
}
