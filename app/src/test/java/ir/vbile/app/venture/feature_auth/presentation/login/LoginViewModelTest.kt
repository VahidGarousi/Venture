package ir.vbile.app.venture.feature_auth.presentation.login

import com.google.common.truth.Truth.assertThat
import ir.vbile.app.venture.MainCoroutineRule
import ir.vbile.app.venture.feature_auth.data.repository.FakeAuthRepository
import ir.vbile.app.venture.feature_auth.domain.repository.AuthRepository
import ir.vbile.app.venture.feature_auth.domain.use_case.LoginUseCaseImpl
import ir.vbile.app.venture.feature_auth.presentation.util.AuthError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {
    private val emptyField = ""
    private val validEmail = "garousiv@gmail.com"
    private val validPassword = "Zxc123Vbn"
    private val invalidEmail = "garousiv@gmail"
    private val invalidPassword = "zxcadvsvs"
    private val weekPassword = "zxca"

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private lateinit var vm: LoginViewModel
    private var repository: AuthRepository = FakeAuthRepository()
    private val loginUseCase = LoginUseCaseImpl(repository)

    @Before
    fun setUp() {
        vm = LoginViewModel(mainCoroutineRule.dispatcher, loginUseCase)
    }

    @Test
    fun `login with empty email field, returns AuthError_FieldEmpty`() {
        mainCoroutineRule.runBlockingTest {
            vm.onEvent(LoginEvent.EnteredEmail(emptyField))
            vm.onEvent(LoginEvent.Login)
            val emailState = vm.emailState.value
            assertThat(emailState.error).isEqualTo(AuthError.FieldEmpty)
        }
    }

    @Test
    fun `login with invalid email field, returns AuthError_InvalidEmail`() {
        mainCoroutineRule.runBlockingTest {
            vm.onEvent(LoginEvent.EnteredEmail(invalidEmail))
            vm.onEvent(LoginEvent.Login)
            val emailState = vm.emailState.value
            assertThat(emailState.error).isEqualTo(AuthError.InvalidEmail)
        }
    }

    @Test
    fun `login with empty password field, returns AuthError_InputTooShort`() {
        mainCoroutineRule.runBlockingTest {
            vm.onEvent(LoginEvent.EnteredPassword(emptyField))
            vm.onEvent(LoginEvent.Login)
            val passwordState = vm.passwordState.value
            assertThat(passwordState.error).isEqualTo(AuthError.FieldEmpty)
        }
    }

    @Test
    fun `login with week password field, returns AuthError_InputTooShort`() {
        mainCoroutineRule.runBlockingTest {
            vm.onEvent(LoginEvent.EnteredPassword(weekPassword))
            vm.onEvent(LoginEvent.Login)
            val passwordState = vm.passwordState.value
            assertThat(passwordState.error).isEqualTo(AuthError.InputTooShort)
        }
    }

    @Test
    fun `login with invalid password field, returns AuthError_InvalidPassword`() {
        mainCoroutineRule.runBlockingTest {
            vm.onEvent(LoginEvent.EnteredPassword(invalidPassword))
            vm.onEvent(LoginEvent.Login)
            val passwordState = vm.passwordState.value
            assertThat(passwordState.error).isEqualTo(AuthError.InvalidPassword)
        }
    }

    @Test
    fun `enter valid email, password, emit  UiEvent_Navigate(Screen_MainFeedScreen_route)`() {
        mainCoroutineRule.runBlockingTest {
            assertThat("1").isEqualTo("2")
        }
    }
}