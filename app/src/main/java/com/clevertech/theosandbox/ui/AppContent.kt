package com.ballyscorp.ballylive.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ballyscorp.ballylive.AppViewModel
import com.ballyscorp.ballylive.navigation.AppNavGraph
import com.ballyscorp.ballylive.navigation.NavEventProvider
import com.ballyscorp.ballylive.navigation.execute
import com.ballyscorp.ballylive.ui.common.theme.BallysTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.DestinationStyle
import com.ramcosta.composedestinations.utils.destination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
@Composable
internal fun AppContent(
    navEventProvider: NavEventProvider,
    viewModel: AppViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val context = LocalContext.current

    val state by viewModel.state.collectAsState()
    LaunchedEffect(navEventProvider, context) {
        navEventProvider.navigationEvent.collect { navEvent ->
            navController.execute(
                navEvent = navEvent,
            )
        }
    }

    
    //TODO: Handler back pressed on AuthWebView
//    if (state.isLoading) {
//        Dialog(
//            onDismissRequest = {}
//        ) {
//            CircularProgressIndicator(
//                color = Color.White
//            )
//        }
//    }
//
//    BackHandler(enabled = true, onBack = { viewModel.onBackPressed() })

    Scaffold(
        // Reset default insets, because app toolbar should be shown behind status bar
        contentWindowInsets = WindowInsets(
            0,
            0,
            0,
            ScaffoldDefaults.contentWindowInsets.getBottom(LocalDensity.current)
        ),
        containerColor = BallysTheme.colors.terrain.white,
    ) { contentPadding ->
        DestinationsNavHost(
            navController = navController,
            navGraph = AppNavGraph,
            modifier = Modifier
                .padding(contentPadding)
                .consumeWindowInsets(contentPadding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        )
    }
}

val NavController.currentNonDialogDestinationFlow: Flow<DestinationSpec<*>?>
    get() = currentBackStackEntryFlow
        .map { currentBackstackEntry ->
            // For dialogs the previous destination must be taken to keep the correct item
            //  selected in the bottom navigation that is visible behind the dialog
            if (currentBackstackEntry.destination().style is DestinationStyle.Dialog) {
                previousBackStackEntry
            } else {
                currentBackstackEntry
            }?.destination()
        }
