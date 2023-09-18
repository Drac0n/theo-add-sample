package com.ballyscorp.ballylive.functionality.shared.navigation

import androidx.navigation.NavOptionsBuilder
import com.ramcosta.composedestinations.spec.Direction

interface AppNavigator {

    /**
     * Should be used to navigate within the same module where the actual Destination/Direction is
     * known. The direction can be retrieved from the generated Compose Destination classes.
     */
    fun navigateTo(
        direction: Direction,
        navOptionsBuilder: NavOptionsBuilder.() -> Unit = {}
    )

    /**
     * Should be used to navigate between Destinations of different modules. Since the actual
     * Destination/Direction of a target module is unknown to the caller module, the target area can
     * be specified here and is resolved later where all the ui modules come together.
     */
    fun navigateTo(
        navTarget: NavTarget,
        navOptionsBuilder: NavOptionsBuilder.() -> Unit = {}
    )

    /**
     * Should be used to navigate back one time in the backstack.
     */
    fun navigateUp()
}
