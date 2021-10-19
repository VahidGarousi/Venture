package ir.vbile.app.venture.platform.mobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.vbile.app.venture.core.presentation.AppNavigation
import ir.vbile.app.venture.core.presentation.ui.component.StandardScaffold
import ir.vbile.app.venture.core.presentation.ui.theme.VentureTheme
import ir.vbile.app.venture.core.util.Screen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VentureTheme(true) {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val scaffoldState = rememberScaffoldState()
                    StandardScaffold(
                        navController = navController,
                        scaffoldState = scaffoldState,
                        onFabClick = { navController.navigate(Screen.PlayGameScreen.route) },
                        showBottomBar = shouldShowBottomBar(navBackStackEntry)
                    ) {
                        AppNavigation(
                            navController = navController,
                            scaffoldState = scaffoldState
                        )
                    }
                }
            }
        }
    }

    private fun shouldShowBottomBar(backStackEntry: NavBackStackEntry?): Boolean {
        val doesRouteMatch = backStackEntry?.destination?.route in listOf(
            Screen.MainFeedScreen.route,
            Screen.ChatScreen.route,
            Screen.ActivityScreen.route
        )
        val isOwnProfile =
            backStackEntry?.destination?.route == "${Screen.ProfileScreen.route}?userId={userId}" && backStackEntry.arguments?.getString(
                "userId"
            ) == null
        return doesRouteMatch || isOwnProfile
    }
}