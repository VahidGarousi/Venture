package ir.vbile.app.venture.feature_auth.domain.use_case

import ir.vbile.app.venture.core.util.SimpleResource

interface AuthenticateUseCase {
    suspend operator fun invoke(): SimpleResource
}