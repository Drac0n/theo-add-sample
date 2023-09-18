package com.ballyscorp.ballylive

import androidx.lifecycle.ViewModel
import com.ballyscorp.ballylive.functionality.shared.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    private val isLoading = MutableStateFlow(false)
    val state = isLoading
}
