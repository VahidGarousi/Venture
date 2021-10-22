package ir.vbile.app.venture.feature_auth.domain.use_case

import ir.vbile.app.venture.core.presentation.util.ValidationUtil
import ir.vbile.app.venture.feature_auth.domain.models.RegisterResult
import ir.vbile.app.venture.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : RegisterUseCase {
    override suspend fun invoke(
        email: String,
        password: String,
        confirmPassword: String
    ): RegisterResult {
        val trimmedEmail = email.trim()
        val trimmedPassword = password.trim()
        val trimmedConfirmedPassword = confirmPassword.trim()
        val emailError = ValidationUtil.validateEmail(trimmedEmail)
        val passwordError = ValidationUtil.validatePassword(trimmedPassword)
        val confirmPasswordError = ValidationUtil.validateConfirmPassword(trimmedConfirmedPassword)
        if (emailError != null || passwordError != null || confirmPasswordError != null) {
            return RegisterResult(
                emailError,
                passwordError,
                confirmPasswordError
            )
        }
        val result = repository.register(
            email = trimmedEmail,
            password = trimmedPassword,
            confirmPassword = trimmedConfirmedPassword
        )
        return RegisterResult(result = result)
    }
}

