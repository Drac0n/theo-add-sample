package com.ballyscorp.ballylive.ui

sealed interface NavType {

    object Tabs : NavType
    object None : NavType
}
