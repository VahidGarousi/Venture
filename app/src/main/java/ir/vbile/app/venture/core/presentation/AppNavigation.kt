package ir.vbile.app.venture.core.presentation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ir.vbile.app.venture.core.util.Screen
import ir.vbile.app.venture.feature_auth.presentation.login.LoginScreen
import ir.vbile.app.venture.feature_auth.presentation.register.RegisterScreen
import ir.vbile.app.venture.feature_auth.presentation.splash.SplashScreen
import ir.vbile.app.venture.feature_post.presentation.main_feed.MainFeedScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(
            route = Screen.SplashScreen.route
        ) {
            SplashScreen(scaffoldState,
                navAction = {
                    processNavigationAction(it, navController)
                }
            )
        }
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(
                scaffoldState = scaffoldState,
                navAction = {
                    processNavigationAction(it,navController)
                }
            )
        }
        composable(
            route = Screen.RegisterScreen.route
        ) {
            RegisterScreen(
                navAction = { processNavigationAction(it, navController) }
            )
        }

        composable(
            Screen.MainFeedScreen.route
        ) {
            MainFeedScreen()
        }
        composable(
            Screen.ChatScreen.route
        ) {

        }
        composable(
            Screen.PlayGameScreen.route
        ) {

        }
        composable(
            Screen.ActivityScreen.route
        ) {

        }
        composable(
            route = Screen.ProfileScreen.route + "?userId={userId}",
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {

        }
    }
}

private fun processNavigationAction(
    action: NavigationActions,
    navController: NavHostController
) {
    when (action) {
        is NavigationActions.Navigate -> navController.navigate(action.route)
        NavigationActions.NavigateUp -> navController.popBackStack()
    }
}