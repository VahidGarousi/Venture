package ir.vbile.app.venture.core.domain.states

import ir.vbile.app.venture.core.util.Error


data class PasswordTextFieldState(
    val text : String = "",
    val error : Error? = null,
    val isPasswordVisible : Boolean = false
)
