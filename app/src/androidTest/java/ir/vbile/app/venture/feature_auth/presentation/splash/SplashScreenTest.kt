package ir.vbile.app.venture.feature_auth.presentation.splash


import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import ir.vbile.app.venture.core.presentation.NavigationActions
import ir.vbile.app.venture.core.presentation.ui.theme.VentureTheme
import ir.vbile.app.venture.core.util.Screen
import ir.vbile.app.venture.core.util.TestTags
import ir.vbile.app.venture.feature_auth.util.AuthConstants
import ir.vbile.app.venture.platform.mobile.presentation.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import  com.google.common.truth.Truth.assertThat

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SplashScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val testDispatcher = TestCoroutineDispatcher()

    @RelaxedMockK
    lateinit var navController: NavController

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun splashScreen_displaysAndDisappears() = testDispatcher.runBlockingTest {
        composeTestRule.setContent {
            VentureTheme {
                SplashScreen(
                    dispatcher = testDispatcher
                ) {
                    when (it) {
                        is NavigationActions.Navigate -> {
                            navController.navigate(it.route)
                            assertThat(it).isEqualTo(NavigationActions.Navigate(Screen.RegisterScreen.route))
                        }
                        NavigationActions.NavigateUp -> {
                            navController.navigateUp()
                            assertThat(it).isEqualTo(NavigationActions.NavigateUp)
                        }
                    }
                }
            }
        }
        composeTestRule.onNodeWithContentDescription(TestTags.SPLASH_SCREEN_LOGO).assertExists()
        advanceTimeBy(AuthConstants.SPLASH_SCREEN_DURATION)
        verify {
            navController.navigateUp()
            navController.navigate(Screen.RegisterScreen.route)
        }
    }
}