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
        startDestination = Screen.SplashScreen.route
    ) {
        composable(
            route = Screen.SplashScreen.route
        ) {
            SplashScreen(scaffoldState = scaffoldState) {
                processNavigationAction(it, navController)
            }
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
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen()
        }
        composable(
            route = Screen.RegisterScreen.route
        ) {
            RegisterScreen()
        }
    }
}

private fun processNavigationAction(
    action: NavigationActions,
    nacController: NavHostController
) {
    when (action) {
        is NavigationActions.Navigate -> nacController.navigate(action.route)
        NavigationActions.NavigateUp -> nacController.navigateUp()
    }
}