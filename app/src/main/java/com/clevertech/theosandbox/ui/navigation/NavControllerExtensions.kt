package com.ballyscorp.ballylive.navigation

import androidx.navigation.NavController
import com.ballyscorp.ballylive.functionality.shared.navigation.NavTarget
import com.ballyscorp.ballylive.ui.NavItemsProvider
import com.ballyscorp.ballylive.ui.currentNonDialogDestinationFlow
import com.clevertech.theosandbox.ui.navGraphSpec
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popBackStack
import com.ramcosta.composedestinations.navigation.popUpTo
import com.ramcosta.composedestinations.utils.startDestination
import kotlinx.coroutines.flow.first

suspend fun NavController.execute(
    navEvent: NavEvent
) {
    when (navEvent) {
        NavEvent.NavigateUp -> navigateUp()
        is NavEvent.NavigateTo.Destination -> {
            navigate(
                direction = navEvent.direction,
                navOptionsBuilder = navEvent.navOptionsBuilder
            )
        }

        is NavEvent.NavigateTo.Target -> {
            when (navEvent.target) {
                is NavTarget.Tab -> navigateToTab(target = navEvent.target)
            }
        }

        else -> {}
    }
}

/**
 * Navigates to a tab and takes care of switching the area and manages the backstack.
 */
private suspend fun NavController.navigateToTab(
    target: NavTarget.Tab,
) {
    val currentDestination = currentNonDialogDestinationFlow.first()

    val currentTabGraph = (NavItemsProvider.tabs)
        .find { it.navGraph.destinationsByRoute.containsValue(currentDestination) }
        ?.navGraph

    val targetDestination = target.navGraphSpec()

    // Ensure we are not on a sub page currently. If so, pop to the root.
    if (currentTabGraph != null && currentTabGraph != targetDestination) {
        popBackStack(route = currentTabGraph.startRoute, inclusive = false)
    }

    // Then change the tab or reload if we are already on the correct tab.
    navigate(targetDestination) {
        // Pop up to the start destination of the current graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(AppNavGraph.startDestination) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
