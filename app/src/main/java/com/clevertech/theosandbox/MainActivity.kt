package com.clevertech.theosandbox

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ballyscorp.ballylive.MainActivityViewModel
import com.ballyscorp.ballylive.navigation.NavEventProvider
import com.ballyscorp.ballylive.ui.AppContent
import com.ballyscorp.ballylive.ui.common.theme.BallysTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    @Inject
    internal lateinit var navEventProvider: NavEventProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        splashScreenWorker(splashScreen)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // call to fetchUserData instead of adding the VM init function because Authentication has a race condition

        setContent {
            BallysTheme {
                val state by viewModel.state.collectAsState()

                val systemUiController = rememberSystemUiController()

                // Update the dark content of the system bars to match the theme
                DisposableEffect(systemUiController) {
                    systemUiController.systemBarsDarkContentEnabled = false
                    systemUiController.setStatusBarColor(color = Color.Transparent)
                    onDispose {}
                }

                    DisposableEffect(systemUiController) {
                        systemUiController.setNavigationBarColor(
                            color = Color.Black,
                        )
                        onDispose {}
                    }

                    // Init compose views.
                    AppContent(navEventProvider = navEventProvider)
            }
        }
    }

    private fun splashScreenWorker(splashScreen: SplashScreen) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                splashScreen.setKeepOnScreenCondition {
                    false
                }
            }
        }
    }

    private fun showSystemBar() {
        with(WindowCompat.getInsetsController(window, window.decorView)) {
            show(WindowInsetsCompat.Type.systemBars())
        }
    }

    private fun hideSystemBar() {
        with(WindowCompat.getInsetsController(window, window.decorView)) {
            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            hide(WindowInsetsCompat.Type.systemBars())
        }
    }
}
