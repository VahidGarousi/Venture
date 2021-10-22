package ir.vbile.app.venture.feature_auth.presentation.register

import ir.vbile.app.venture.core.util.UiText

data class RegisterState(
    val successful: Boolean? = null,
    val message: UiText? = null,
    val isLoading: Boolean = false
)
