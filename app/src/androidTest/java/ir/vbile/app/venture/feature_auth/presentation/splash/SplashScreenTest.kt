package ir.vbile.app.venture.feature_auth.presentation.splash


import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import ir.vbile.app.venture.core.presentation.NavigationActions
import ir.vbile.app.venture.core.presentation.ui.theme.VentureTheme
import ir.vbile.app.venture.core.util.Screen
import ir.vbile.app.venture.core.util.TestTags
import ir.vbile.app.venture.feature_auth.domain.use_cases.FakeAuthenticateUseCaseTest
import ir.vbile.app.venture.feature_auth.util.AuthConstants
import ir.vbile.app.venture.platform.mobile.presentation.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SplashScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val testDispatcher = TestCoroutineDispatcher()

    private val testCoroutineScope = TestCoroutineScope()

    private lateinit var vm: SplashViewModel

    @RelaxedMockK
    lateinit var navController: NavController

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        vm = SplashViewModel(
            authenticateUseCase = FakeAuthenticateUseCaseTest(),
            defaultDispatcher = testDispatcher
        )
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
        testCoroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun splashScreen_displaysAndDisappears() = testDispatcher.runBlockingTest {
        composeTestRule.setContent {
            val scaffoldState = rememberScaffoldState()
            VentureTheme {
                SplashScreen(
                    dispatcher = testDispatcher,
                    scaffoldState = scaffoldState
                ) { action ->
                    when (action) {
                        is NavigationActions.Navigate -> {
                            navController.navigate(action.route)
                            assertThat(action).isEqualTo(NavigationActions.Navigate(Screen.RegisterScreen.route))
                        }
                        NavigationActions.NavigateUp -> {
                            navController.navigateUp()
                            assertThat(action).isEqualTo(NavigationActions.NavigateUp)
                        }
                    }
                }
            }
        }
        composeTestRule.onNodeWithContentDescription(TestTags.SPLASH_SCREEN_LOGO).assertExists()
        advanceTimeBy(AuthConstants.SPLASH_SCREEN_DURATION)
    }

    @Test
    fun authenticate_navigateToMainFeedScreenOrLoginScreen() = testDispatcher.runBlockingTest {
        advanceTimeBy(AuthConstants.SPLASH_SCREEN_DURATION)
        composeTestRule.setContent {
            val scaffoldState = rememberScaffoldState()
            VentureTheme {
                SplashScreen(
                    dispatcher = testDispatcher,
                    scaffoldState = scaffoldState,
                    vm = vm
                ) { action ->
                    processNavActions(action)
                }
            }
        }
        verify {
            navController.navigateUp()
            navController.navigate(Screen.MainFeedScreen.route)
        }
    }

    private fun processNavActions(action: NavigationActions) {
        when (action) {
            is NavigationActions.Navigate -> {
                navController.navigate(action.route)
            }
            NavigationActions.NavigateUp -> {
                navController.navigateUp()
            }
        }
    }
}