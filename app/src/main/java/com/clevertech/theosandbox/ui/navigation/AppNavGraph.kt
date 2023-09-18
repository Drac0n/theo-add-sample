package com.ballyscorp.ballylive.navigation

import com.ballyscorp.ballylive.ui.social.AppNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

internal object AppNavGraph : NavGraphSpec by NavGraph(
    route = "app",
    startRoute = AppNavGraph,
    nestedNavGraphs = listOf(
        AppNavGraph,
    )
)

internal data class NavGraph(
    override val route: String,
    override val startRoute: Route,
    val destinations: List<DestinationSpec<*>> = emptyList(),
    override val nestedNavGraphs: List<NavGraphSpec> = emptyList()
) : NavGraphSpec {

    override val destinationsByRoute: Map<String, DestinationSpec<*>> = destinations.associateBy { it.route }
}
