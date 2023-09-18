package com.ballyscorp.ballylive.functionality.shared.navigation

sealed interface NavTarget {

    sealed class Tab(
    ) : NavTarget {
        object Social : Tab()
    }
}
