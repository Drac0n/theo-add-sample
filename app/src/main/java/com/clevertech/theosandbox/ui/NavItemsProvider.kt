package com.ballyscorp.ballylive.ui

import com.ballyscorp.ballylive.functionality.shared.navigation.NavTarget
import com.clevertech.theosandbox.R
import com.clevertech.theosandbox.ui.navGraphSpec
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.utils.startDestination

object NavItemsProvider {

    val tabs = listOf(
        NavItem(
            name = R.string.bottom_bar_item_social,
            icon = R.drawable.ic_home,
            navTarget = NavTarget.Tab.Social
        )

    )

    fun getNavigationType(destination: DestinationSpec<*>?): NavType = when {
        tabs.containsAtRoot(destination) -> NavType.Tabs
        else -> NavType.None
    }

    private fun List<NavItem>.containsAtRoot(destination: DestinationSpec<*>?) =
        map { it.navTarget.navGraphSpec().startDestination }.contains(destination)
}
