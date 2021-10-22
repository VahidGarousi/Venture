package ir.vbile.app.venture.feature_auth.presentation.register

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import ir.vbile.app.venture.R
import ir.vbile.app.venture.MainCoroutineRule
import ir.vbile.app.venture.core.util.UiEvent
import ir.vbile.app.venture.core.util.UiText
import ir.vbile.app.venture.feature_auth.data.repository.FakeAuthRepository
import ir.vbile.app.venture.feature_auth.domain.repository.AuthRepository
import ir.vbile.app.venture.feature_auth.domain.use_cases.FakeRegisterUseCase
import ir.vbile.app.venture.feature_auth.presentation.util.AuthError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class RegisterViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var vm: RegisterViewModel
    private var repository: AuthRepository = FakeAuthRepository()
    private val registerUseCase = FakeRegisterUseCase(repository)

    private val emptyField = ""
    private val validEmail = "garousiv@gmail.com"
    private val validPassword = "Zxc123Vbn"
    private val validConfirmPassword = "Zxc123Vbn"
    private val invalidEmail = "garousiv@gmail"
    private val invalidPassword = "zxcadvsvs"
    private val weekPassword = "zxca"
    private val invalidConfirmPassword = "zxcadvsvs"
    private val weekConfirmPassword = "zxca"

    @Before
    fun setUp() {
        vm = RegisterViewModel(registerUseCase, mainCoroutineRule.dispatcher)
    }

    @Test
    fun `register with empty email field, returns AuthError_FieldEmpty`() {
        mainCoroutineRule.runBlockingTest {
            vm.onEvent(RegisterEvent.EnteredEmail(emptyField))
            vm.onEvent(RegisterEvent.Register)
            val emailState = vm.emailState.value
            assertThat(emailState.error).isEqualTo(AuthError.FieldEmpty)
        }
    }

    @Test
    fun `register with invalid email field, returns AuthError_InvalidEmail`() =
        mainCoroutineRule.runBlockingTest {
            vm.onEvent(RegisterEvent.EnteredEmail(invalidEmail))
            vm.onEvent(RegisterEvent.Register)
            val emailState = vm.emailState.value
            assertThat(emailState.error).isEqualTo(AuthError.InvalidEmail)
        }

    @Test
    fun `register with empty password field, returns AuthError_FieldEmpty`() =
        mainCoroutineRule.runBlockingTest {
            vm.onEvent(RegisterEvent.EnteredPassword(emptyField))
            vm.onEvent(RegisterEvent.Register)
            val passwordState = vm.passwordState.value
            assertThat(passwordState.error).isEqualTo(AuthError.FieldEmpty)
        }

    @Test
    fun `register with invalid password filed, returns AuthError_InvalidPassword`() =
        mainCoroutineRule.runBlockingTest {
            vm.onEvent(RegisterEvent.EnteredPassword(invalidPassword)) // password must contains  capital letters and small letter and numbers
            vm.onEvent(RegisterEvent.Register)
            val passwordState = vm.passwordState.value
            assertThat(passwordState.error).isEqualTo(AuthError.InvalidPassword)
        }

    @Test
    fun `register with a password that is less than the minimum number allowed, returns AuthError_InputTooShort`() =
        mainCoroutineRule.runBlockingTest {
            vm.onEvent(RegisterEvent.EnteredPassword(weekPassword))
            vm.onEvent(RegisterEvent.Register)
            val passwordState = vm.passwordState.value
            assertThat(passwordState.error).isEqualTo(AuthError.InputTooShort)
        }

    @Test
    fun `register with empty confirm password field, returns AuthError_FieldEmpty`() =
        mainCoroutineRule.runBlockingTest {
            vm.onEvent(RegisterEvent.EnteredConfirmedPassword(emptyField))
            vm.onEvent(RegisterEvent.Register)
            val confirmedPasswordState = vm.confirmedPasswordState.value
            assertThat(confirmedPasswordState.error).isEqualTo(AuthError.FieldEmpty)
        }

    @Test
    fun `register with week confirm confirm password that is less than the minimum number allowed, returns AuthError_InputTooShort`() =
        mainCoroutineRule.runBlockingTest {
            vm.onEvent(RegisterEvent.EnteredConfirmedPassword(weekConfirmPassword))
            vm.onEvent(RegisterEvent.Register)
            val confirmedPasswordState = vm.confirmedPasswordState.value
            assertThat(confirmedPasswordState.error).isEqualTo(AuthError.InputTooShort)
        }


    @ExperimentalTime
    @Test
    fun `valid inputs, eventFlow must emit successfully message`() =
        mainCoroutineRule.runBlockingTest {
            val registerState = vm.eventFlow
            vm.onEvent(RegisterEvent.EnteredEmail(validEmail))
            vm.onEvent(RegisterEvent.EnteredPassword(validPassword))
            vm.onEvent(RegisterEvent.EnteredConfirmedPassword(validConfirmPassword))
            vm.onEvent(RegisterEvent.Register)
            registerState.test {
                advanceTimeBy(1000)
                assertEquals(
                    UiEvent.ShowSnackBar(UiText.StringResource(R.string.successfully_registered_an_account)),
                    awaitItem()
                )
            }
        }
}