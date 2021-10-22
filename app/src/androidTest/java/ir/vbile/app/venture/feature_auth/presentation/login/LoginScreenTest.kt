package ir.vbile.app.venture.feature_auth.presentation.login

import android.content.res.Resources
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import ir.vbile.app.venture.R
import ir.vbile.app.venture.core.presentation.NavigationActions
import ir.vbile.app.venture.core.presentation.ui.theme.VentureTheme
import ir.vbile.app.venture.core.util.Screen
import ir.vbile.app.venture.core.util.TestTags
import ir.vbile.app.venture.core.util.TestTags.EMAIL_STANDARD_TEXT_FIELD
import ir.vbile.app.venture.core.util.TestTags.PASSWORD_STANDARD_TEXT_FIELD
import ir.vbile.app.venture.feature_auth.data.repository.FakeAuthRepository
import ir.vbile.app.venture.feature_auth.domain.repository.AuthRepository
import ir.vbile.app.venture.feature_auth.domain.use_case.LoginUseCaseImpl
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
class LoginScreenTest {
    private lateinit var resource: Resources

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val testDispatcher = TestCoroutineDispatcher()

    private var repository: AuthRepository = FakeAuthRepository()
    private val loginUseCase = LoginUseCaseImpl(repository)

    private val testCoroutineScope = TestCoroutineScope()

    private lateinit var vm: LoginViewModel

    @RelaxedMockK
    lateinit var navController: NavController

    private val emptyField = ""
    private val validEmail = "garousiv@gmail.com"
    private val validPassword = "Zxc123Vbn"
    private val invalidEmail = "garousiv@gmail"
    private val invalidPassword = "zxcadvsvs"
    private val weekPassword = "zxca"

    @Before
    fun setUp() {
        resource = composeTestRule.activity.resources
        MockKAnnotations.init(this)
        vm = LoginViewModel(
            defaultDispatcher = testDispatcher,
            loginUseCase = loginUseCase
        )
        composeTestRule.setContent {
            VentureTheme(true) {
                LoginScreen(
                    vm = vm,
                    navAction = { action ->
                        when (action) {
                            is NavigationActions.Navigate -> navController.navigate(action.route)
                            NavigationActions.NavigateUp -> navController.navigateUp()
                        }
                    },
                    scaffoldState = rememberScaffoldState()
                )
            }
        }
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
        testCoroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun clickOnSignUpButton_navigateTo_loginScreen() = testDispatcher.runBlockingTest {
        composeTestRule
            .onNodeWithTag(TestTags.BUTTON_SIGN_UP)
            .performClick()
        verify {
            navController.navigateUp()
        }
    }

    @Test
    fun enterInvalidPassword_setInvalidPasswordTo_passwordErrorTextField() =
        testDispatcher.runBlockingTest {
            composeTestRule
                .onNodeWithTag(PASSWORD_STANDARD_TEXT_FIELD)
                .performTextClearance()
            composeTestRule
                .onNodeWithTag(PASSWORD_STANDARD_TEXT_FIELD)
                .performTextInput(invalidPassword)
            composeTestRule
                .onNodeWithTag(TestTags.BUTTON_LOGIN)
                .performClick()
            composeTestRule
                .onNodeWithTag(TestTags.ERROR_PASSWORD_TAG)
                .assertTextEquals(resource.getString(R.string.invalid_password))
        }

    @Test
    fun enterTooShortPassword_setInputTooShortTo_passwordErrorTextField() =
        testDispatcher.runBlockingTest {
            composeTestRule
                .onNodeWithTag(PASSWORD_STANDARD_TEXT_FIELD)
                .performTextClearance()
            composeTestRule
                .onNodeWithTag(PASSWORD_STANDARD_TEXT_FIELD)
                .performTextInput(weekPassword)
            composeTestRule
                .onNodeWithTag(TestTags.BUTTON_LOGIN)
                .performClick()
            composeTestRule
                .onNodeWithTag(TestTags.ERROR_PASSWORD_TAG)
                .assertTextEquals(
                    resource.getString(
                        R.string.input_too_short,
                        AuthConstants.MIN_PASSWORD_LENGTH
                    )
                )
        }

    @Test
    fun enterEmptyPassword_setFiledEmptyTo_passwordErrorTextField() =
        testDispatcher.runBlockingTest {
            composeTestRule
                .onNodeWithTag(PASSWORD_STANDARD_TEXT_FIELD)
                .performTextClearance()
            composeTestRule
                .onNodeWithTag(PASSWORD_STANDARD_TEXT_FIELD)
                .performTextInput(emptyField)
            composeTestRule
                .onNodeWithTag(TestTags.BUTTON_LOGIN)
                .performClick()
            composeTestRule
                .onNodeWithTag(TestTags.ERROR_PASSWORD_TAG)
                .assertTextEquals(resource.getString(R.string.error_field_empty))
        }

    @Test
    fun enterInvalidEmail_setInvalidEmailTo_emailErrorTextField() = testDispatcher.runBlockingTest {
        composeTestRule
            .onNodeWithTag(EMAIL_STANDARD_TEXT_FIELD)
            .performTextClearance()
        composeTestRule
            .onNodeWithTag(EMAIL_STANDARD_TEXT_FIELD)
            .performTextInput(invalidEmail)
        composeTestRule
            .onNodeWithTag(TestTags.BUTTON_LOGIN)
            .performClick()
        composeTestRule
            .onNodeWithTag(TestTags.ERROR_EMAIL_TAG)
            .assertTextEquals(resource.getString(R.string.invalid_email))
    }

    @Test
    fun enterEmptyEmail_setFieldEmptyTo_emailErrorTextField() = testDispatcher.runBlockingTest {
        composeTestRule
            .onNodeWithTag(EMAIL_STANDARD_TEXT_FIELD)
            .performTextClearance()
        composeTestRule
            .onNodeWithTag(EMAIL_STANDARD_TEXT_FIELD)
            .performTextInput(emptyField)
        composeTestRule
            .onNodeWithTag(TestTags.BUTTON_LOGIN)
            .performClick()
        composeTestRule
            .onNodeWithTag(TestTags.ERROR_EMAIL_TAG)
            .assertTextEquals(resource.getString(R.string.error_field_empty))
    }

    @Test
    fun enterValidEmail_enterValidPassword_navigateToMainFeedScreen() =
        testDispatcher.runBlockingTest {
            composeTestRule
                .onNodeWithTag(EMAIL_STANDARD_TEXT_FIELD)
                .performTextClearance()
            composeTestRule
                .onNodeWithTag(EMAIL_STANDARD_TEXT_FIELD)
                .performTextInput(validEmail)
            composeTestRule
                .onNodeWithTag(PASSWORD_STANDARD_TEXT_FIELD)
                .performTextClearance()
            composeTestRule
                .onNodeWithTag(PASSWORD_STANDARD_TEXT_FIELD)
                .performTextInput(validPassword)
            composeTestRule
                .onNodeWithTag(TestTags.BUTTON_LOGIN)
                .performClick()
            verify {
                navController.navigate(Screen.MainFeedScreen.route)
            }
        }

}