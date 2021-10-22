package ir.vbile.app.venture.feature_auth.domain.models

import ir.vbile.app.venture.core.util.SimpleResource
import ir.vbile.app.venture.feature_auth.presentation.util.AuthError

data class LoginResult(
    val emailError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: SimpleResource? = null
)
