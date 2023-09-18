package com.clevertech.theosandbox.ui

import com.ballyscorp.ballylive.functionality.shared.navigation.NavTarget
import com.ballyscorp.ballylive.ui.social.AppNavGraph
import com.ramcosta.composedestinations.spec.NavGraphSpec

fun NavTarget.Tab.navGraphSpec(): NavGraphSpec {
    return when (this) {
        NavTarget.Tab.Social -> AppNavGraph
    }
}
