package ir.vbile.app.venture.feature_auth.data.repository

import ir.vbile.app.venture.core.util.Resource
import ir.vbile.app.venture.core.util.SimpleResource
import ir.vbile.app.venture.core.util.UiText
import ir.vbile.app.venture.feature_auth.domain.repository.AuthRepository
import kotlin.random.Random

class DemoAuthRepositoryImpl : AuthRepository {
    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): SimpleResource {
        TODO("Not yet implemented")
    }

    override suspend fun login(email: String, password: String): SimpleResource {
        TODO("Not yet implemented")
    }

    override suspend fun authenticate(): SimpleResource {
        return when ((0..1).random()) {
            0 -> Resource.Error(uiText = UiText.unknownError())
            else -> Resource.Success(Unit)
        }
    }
}