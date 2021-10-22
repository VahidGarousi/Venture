package ir.vbile.app.venture.feature_auth.domain.repository

import ir.vbile.app.venture.core.util.SimpleResource

interface AuthRepository {
    suspend fun register(
        email: String,
        password: String,
        confirmPassword : String
    ): SimpleResource

    suspend fun login(
        email: String,
        password: String
    ): SimpleResource

    suspend fun authenticate(): SimpleResource
}