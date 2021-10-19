package ir.vbile.app.venture.core.presentation

sealed class NavigationActions {
    object NavigateUp : NavigationActions()
    data class Navigate(val route: String) : NavigationActions()
}