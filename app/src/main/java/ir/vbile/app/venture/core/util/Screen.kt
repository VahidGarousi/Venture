package ir.vbile.app.venture.core.util

internal sealed class Screen(val route: String) {
    object MainFeedScreen : Screen("main_feed_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object PlayGameScreen : Screen("play_game_screen")
    object ChatScreen : Screen("chat_screen")
    object ActivityScreen : Screen("activity_screen")
    object ProfileScreen : Screen("profile_screen")
    object SplashScreen : Screen("profile_screen")
}