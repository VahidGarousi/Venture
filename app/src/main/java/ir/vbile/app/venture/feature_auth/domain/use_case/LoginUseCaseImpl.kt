package ir.vbile.app.venture.feature_auth.domain.use_case

import ir.vbile.app.venture.core.presentation.util.ValidationUtil
import ir.vbile.app.venture.feature_auth.domain.models.LoginResult
import ir.vbile.app.venture.feature_auth.domain.repository.AuthRepository

class LoginUseCaseImpl(
    private val repository: AuthRepository
) : LoginUseCase {
    override suspend fun invoke(email: String, password: String): LoginResult {
        val trimmedEmail = email.trim()
        val trimmedPassword = password.trim()
        val emailError = ValidationUtil.validateEmail(trimmedEmail)
        val passwordError = ValidationUtil.validatePassword(trimmedPassword)
        if (emailError != null || passwordError != null) {
            return LoginResult(emailError, passwordError, null)
        }
        val result = repository.login(email, password)
        return LoginResult(result = result)
    }
}