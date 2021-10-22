package ir.vbile.app.venture.feature_auth.domain.use_case

import ir.vbile.app.venture.feature_auth.domain.models.LoginResult

interface LoginUseCase {
    suspend operator fun invoke(
        email: String,
        password: String
    ): LoginResult
}