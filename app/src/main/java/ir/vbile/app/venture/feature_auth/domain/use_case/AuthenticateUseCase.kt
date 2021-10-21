package ir.vbile.app.venture.feature_auth.domain.use_case

import ir.vbile.app.venture.core.domain.use_cases.BaseUseCase
import ir.vbile.app.venture.core.util.SimpleResource
import ir.vbile.app.venture.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthenticateUseCase @Inject constructor(
    private val repository: AuthRepository
) : BaseUseCase<SimpleResource> {
    override suspend fun invoke(): SimpleResource {
        return repository.authenticate()
    }
}
