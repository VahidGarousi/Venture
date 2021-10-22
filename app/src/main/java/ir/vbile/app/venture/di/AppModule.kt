package ir.vbile.app.venture.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.vbile.app.venture.core.util.CoreConstants
import ir.vbile.app.venture.core.util.CoreConstants.SHARE_PREFERENCES_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainCoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharePreferences(
        @ApplicationContext context: Context
    ): SharedPreferences =
        context.getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE)


    @Provides
    @Singleton
    fun provideOkHttpClient(sharedPreferences: SharedPreferences): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor {
                val token = sharedPreferences.getString(CoreConstants.KEY_JWT_TOKEN, "")
                val modifiedRequest = it.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                it.proceed(modifiedRequest)
            }
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    @Named("mainScope")
    fun provideMainCoroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.Main)
}