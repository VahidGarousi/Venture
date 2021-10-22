package ir.vbile.app.venture.feature_auth.domain.use_case

import ir.vbile.app.venture.feature_auth.domain.models.RegisterResult

interface RegisterUseCase {
    suspend operator fun invoke(
        email: String,
        password: String,
        confirmPassword: String
    ): RegisterResult
}