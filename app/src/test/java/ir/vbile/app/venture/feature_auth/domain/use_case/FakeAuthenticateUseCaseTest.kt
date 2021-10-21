package ir.vbile.app.venture.feature_auth.domain.use_case

import ir.vbile.app.venture.core.domain.use_cases.BaseUseCase
import ir.vbile.app.venture.core.util.Resource
import ir.vbile.app.venture.core.util.SimpleResource
import ir.vbile.app.venture.core.util.UiText

class FakeAuthenticateUseCaseTest(
    private val returnSuccess: Boolean = true
) : BaseUseCase<SimpleResource> {
    override suspend fun invoke(): SimpleResource {
        return if (returnSuccess) Resource.Success(Unit) else Resource.Error(UiText.unknownError())
    }
}
