package ir.vbile.app.venture.feature_auth.domain.use_case

import ir.vbile.app.venture.core.domain.use_cases.BaseUseCase
import ir.vbile.app.venture.core.util.Resource
import ir.vbile.app.venture.core.util.SimpleResource

class FakeAuthenticateUseCase : BaseUseCase<SimpleResource> {
    override suspend fun invoke(): SimpleResource {
        return Resource.Success(Unit)
    }
}
