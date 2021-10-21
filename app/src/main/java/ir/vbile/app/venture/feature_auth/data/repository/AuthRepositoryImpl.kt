package ir.vbile.app.venture.feature_auth.data.repository

import ir.vbile.app.venture.R
import ir.vbile.app.venture.core.util.Resource
import ir.vbile.app.venture.core.util.SimpleResource
import ir.vbile.app.venture.core.util.UiText
import ir.vbile.app.venture.feature_auth.data.remote.AuthApi
import ir.vbile.app.venture.feature_auth.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {
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
        return try {
            api.authenticate()
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Error(UiText.StringResource(R.string.error_couldnt_reach))
        } catch (e: HttpException) {
            Resource.Error(UiText.StringResource(R.string.oops_something_went_wrong))
        }
    }
}