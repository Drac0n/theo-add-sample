package com.ballyscorp.ballylive

data class MainActivityState(
    val isLoadingAppConfigs: Boolean = true,
    val hasToUpdate: Boolean = false,
    val minimumVersionMessage: String = "",
)
