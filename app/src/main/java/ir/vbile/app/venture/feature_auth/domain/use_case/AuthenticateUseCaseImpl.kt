package ir.vbile.app.venture.feature_auth.domain.use_case

import ir.vbile.app.venture.core.util.Resource
import ir.vbile.app.venture.core.util.SimpleResource
import ir.vbile.app.venture.core.util.UiText
import ir.vbile.app.venture.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthenticateUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : AuthenticateUseCase {
    override suspend fun invoke(): SimpleResource {
        return Resource.Error(UiText.unknownError())
    }
}
