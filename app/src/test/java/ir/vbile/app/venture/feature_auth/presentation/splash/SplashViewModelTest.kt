package ir.vbile.app.venture.feature_auth.presentation.splash

import com.google.common.truth.Truth.assertThat
import ir.vbile.app.venture.core.MainCoroutineRule
import ir.vbile.app.venture.core.util.Screen
import ir.vbile.app.venture.core.util.UiEvent
import ir.vbile.app.venture.feature_auth.domain.use_cases.FakeAuthenticateUseCaseTest
import ir.vbile.app.venture.feature_auth.util.AuthConstants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SplashViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var vm: SplashViewModel

    @Before
    fun setUp() {
    }

    @Test
    fun authenticateFirst_receiveSuccess_navigateToMainScreen() = mainCoroutineRule.runBlockingTest {
        vm = SplashViewModel(FakeAuthenticateUseCaseTest(true), mainCoroutineRule.dispatcher)
        advanceTimeBy(AuthConstants.SPLASH_SCREEN_DURATION)
        val uiState = (vm.uiState.value) as UiEvent.Navigate
        assertThat(uiState.route).isEqualTo(Screen.MainFeedScreen.route)
    }

    @Test
    fun authenticateFirst_receiveError_navigateToRegisterScreen() = mainCoroutineRule.runBlockingTest {
        vm = SplashViewModel(FakeAuthenticateUseCaseTest(false), mainCoroutineRule.dispatcher)
        advanceTimeBy(AuthConstants.SPLASH_SCREEN_DURATION)
        val uiState = (vm.uiState.value) as UiEvent.Navigate
        assertThat(uiState.route).isEqualTo(Screen.RegisterScreen.route)
    }
}