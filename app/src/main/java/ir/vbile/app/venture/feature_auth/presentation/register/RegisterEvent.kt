package ir.vbile.app.venture.feature_auth.presentation.register

sealed class RegisterEvent {
    data class EnteredEmail(val email: String) : RegisterEvent()
    data class EnteredPassword(val password: String) : RegisterEvent()
    data class EnteredConfirmedPassword(val password: String) : RegisterEvent()
    object TogglePasswordVisibility : RegisterEvent()
    object ToggleConfirmedPasswordVisibility : RegisterEvent()
    object Register : RegisterEvent()
}
