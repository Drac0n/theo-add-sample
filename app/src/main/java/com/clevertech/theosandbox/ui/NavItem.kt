package com.ballyscorp.ballylive.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ballyscorp.ballylive.functionality.shared.navigation.NavTarget
import com.clevertech.theosandbox.ui.navGraphSpec

data class NavItem(
    @StringRes val name: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val iconSelected: Int? = null, // Rewards have different icon for selected state
    val navTarget: NavTarget.Tab,
) {

    val navGraph = navTarget.navGraphSpec()
}
