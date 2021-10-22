package ir.vbile.app.venture.feature_auth.data.remote

import ir.vbile.app.venture.core.data.dto.responses.BasicApiResponse
import ir.vbile.app.venture.feature_auth.data.remote.response.AuthResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/user/create")
    suspend fun register(): BasicApiResponse<Unit>

    @POST("/api/user/login")
    suspend fun login(): BasicApiResponse<AuthResponse>

    @GET("/api/user/authenticate")
    suspend fun authenticate()

    companion object {
        const val BASE_URL = "http://10.0.2.2:8001/"
    }
}