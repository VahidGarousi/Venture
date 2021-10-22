package ir.vbile.app.venture.feature_auth.presentation.util

import ir.vbile.app.venture.core.util.Error


sealed class AuthError : Error() {
    object FieldEmpty : AuthError()
    object InputTooShort : AuthError()
    object InvalidEmail : AuthError()
    object InvalidPassword : AuthError()
    object InvalidConfirmPassword : AuthError()
    object InvalidPasswordAndConfirmPasswordDoesNotTheSame : AuthError()
}
