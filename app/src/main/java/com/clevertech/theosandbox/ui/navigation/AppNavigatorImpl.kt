package com.ballyscorp.ballylive.navigation

import androidx.navigation.NavOptionsBuilder
import com.ballyscorp.ballylive.functionality.shared.navigation.AppNavigator
import com.ballyscorp.ballylive.functionality.shared.navigation.NavTarget
import com.ramcosta.composedestinations.spec.Direction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AppNavigatorImpl @Inject constructor() : AppNavigator, NavEventProvider {

    private val navigationEventChannel = Channel<NavEvent>(
        capacity = Channel.CONFLATED
    )
    override val navigationEvent = navigationEventChannel.receiveAsFlow()

    override fun navigateTo(
        direction: Direction,
        navOptionsBuilder: NavOptionsBuilder.() -> Unit
    ) {
        navigationEventChannel.trySend(
            NavEvent.NavigateTo.Destination(
                direction = direction,
                navOptionsBuilder = navOptionsBuilder
            )
        )
    }

    override fun navigateTo(
        navTarget: NavTarget,
        navOptionsBuilder: NavOptionsBuilder.() -> Unit
    ) {
        navigationEventChannel.trySend(
            NavEvent.NavigateTo.Target(
                target = navTarget,
                navOptionsBuilder = navOptionsBuilder
            )
        )
    }

    override fun navigateUp() {
        navigationEventChannel.trySend(NavEvent.NavigateUp)
    }
}
