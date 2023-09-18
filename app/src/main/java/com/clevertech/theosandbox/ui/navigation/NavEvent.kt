package com.ballyscorp.ballylive.navigation

import androidx.navigation.NavOptionsBuilder
import com.ballyscorp.ballylive.functionality.shared.navigation.NavTarget
import com.ramcosta.composedestinations.spec.Direction

sealed interface NavEvent {

    object NavigateUp : NavEvent

    sealed interface NavigateTo : NavEvent {

        data class Target(
            val target: NavTarget,
            val navOptionsBuilder: NavOptionsBuilder.() -> Unit
        ) : NavEvent

        data class Destination(
            val direction: Direction,
            val navOptionsBuilder: NavOptionsBuilder.() -> Unit
        ) : NavEvent
    }
}
