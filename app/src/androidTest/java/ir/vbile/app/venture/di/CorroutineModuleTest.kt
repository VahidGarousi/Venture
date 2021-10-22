package ir.vbile.app.venture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import javax.inject.Named
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@TestInstallIn(components = [SingletonComponent::class], replaces = [CoroutineModule::class])
@Module
object CoroutineModuleTest {


    @Provides
    @Singleton
    fun provideTestDispatcher(): TestCoroutineDispatcher = TestCoroutineDispatcher()

    @Provides
    @Singleton
    @Named("io")
    fun provideIoDispatcher(testCoroutineDispatcher: TestCoroutineDispatcher): CoroutineDispatcher =
        testCoroutineDispatcher

    @Provides
    @Singleton
    @Named("immediateMain")
    fun provideMainDispatcher(testCoroutineDispatcher: TestCoroutineDispatcher): CoroutineDispatcher =
        testCoroutineDispatcher

    @Provides
    @Singleton
    fun provideCoroutineScope(@Named("io") ioDispatcher: CoroutineDispatcher): CoroutineScope =
        TestCoroutineScope(ioDispatcher + SupervisorJob())
}