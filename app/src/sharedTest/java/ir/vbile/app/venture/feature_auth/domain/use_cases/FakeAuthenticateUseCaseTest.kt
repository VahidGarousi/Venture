package ir.vbile.app.venture.feature_auth.domain.use_cases

import ir.vbile.app.venture.core.util.Resource
import ir.vbile.app.venture.core.util.SimpleResource
import ir.vbile.app.venture.core.util.UiText
import ir.vbile.app.venture.feature_auth.domain.use_case.AuthenticateUseCase

class FakeAuthenticateUseCaseTest(
    private val returnSuccess: Boolean = true
) : AuthenticateUseCase {
    override suspend fun invoke(): SimpleResource {
        return if (returnSuccess) Resource.Success(Unit) else Resource.Error(UiText.unknownError())
    }
}
