package ir.vbile.app.venture.feature_auth.domain.use_cases

import ir.vbile.app.venture.feature_auth.domain.models.LoginResult
import ir.vbile.app.venture.feature_auth.domain.use_case.LoginUseCase

class FakeLoginUseCase : LoginUseCase {
    override suspend fun invoke(email: String, password: String): LoginResult {
        return LoginResult()
    }
}