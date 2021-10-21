package ir.vbile.app.venture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.vbile.app.venture.BuildConfig
import ir.vbile.app.venture.core.domain.use_cases.BaseUseCase
import ir.vbile.app.venture.core.util.SimpleResource
import ir.vbile.app.venture.feature_auth.data.remote.AuthApi
import ir.vbile.app.venture.feature_auth.data.repository.AuthRepositoryImpl
import ir.vbile.app.venture.feature_auth.data.repository.DemoAuthRepositoryImpl
import ir.vbile.app.venture.feature_auth.domain.repository.AuthRepository
import ir.vbile.app.venture.feature_auth.domain.use_case.AuthenticateUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideAuthApi(
        client: OkHttpClient
    ): AuthApi =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(AuthApi.BASE_URL)
            .build()
            .create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository = if (!BuildConfig.DEMO_MODE) impl else DemoAuthRepositoryImpl()

    @Provides
    @Singleton
    fun provideAuthenticateUseCase(
        repository: AuthRepository
    ): BaseUseCase<SimpleResource> {
        return AuthenticateUseCase(repository)
    }
}