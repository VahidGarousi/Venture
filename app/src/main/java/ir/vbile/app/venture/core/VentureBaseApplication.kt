package ir.vbile.app.venture.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ir.vbile.app.venture.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class VentureBaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}