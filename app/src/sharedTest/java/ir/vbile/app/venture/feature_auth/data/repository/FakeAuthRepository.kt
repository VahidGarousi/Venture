package ir.vbile.app.venture.feature_auth.data.repository

import ir.vbile.app.venture.core.util.Resource
import ir.vbile.app.venture.core.util.SimpleResource
import ir.vbile.app.venture.feature_auth.domain.repository.AuthRepository

class FakeAuthRepository : AuthRepository {
    override suspend fun register(
        email: String,
        password: String,
        confirmPassword: String
    ): SimpleResource {
        return Resource.Success(Unit)
    }

    override suspend fun login(email: String, password: String): SimpleResource {
        return Resource.Success(Unit)
    }

    override suspend fun authenticate(): SimpleResource {
        return Resource.Success(Unit)
    }
}