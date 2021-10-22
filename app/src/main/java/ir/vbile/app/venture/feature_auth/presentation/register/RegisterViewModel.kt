package ir.vbile.app.venture.feature_auth.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.venture.R
import ir.vbile.app.venture.core.domain.states.PasswordTextFieldState
import ir.vbile.app.venture.core.domain.states.StandardTextFieldsState
import ir.vbile.app.venture.core.util.Resource
import ir.vbile.app.venture.core.util.UiEvent
import ir.vbile.app.venture.core.util.UiText
import ir.vbile.app.venture.feature_auth.domain.use_case.RegisterUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
data class State(
    val string: String = ""
)
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    @Named("immediateMain") private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _emailState = mutableStateOf(StandardTextFieldsState())
    val emailState: State<StandardTextFieldsState> = _emailState


    private val _passwordState = mutableStateOf(PasswordTextFieldState())
    val passwordState: State<PasswordTextFieldState> = _passwordState

    private val _confirmedPasswordState = mutableStateOf(PasswordTextFieldState())
    val confirmedPasswordState: State<PasswordTextFieldState> = _confirmedPasswordState


    private val _registerState = mutableStateOf(RegisterState())
    val registerState: State<RegisterState> = _registerState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnteredEmail -> {
                _emailState.value = _emailState.value.copy(
                    text = event.email
                )
            }
            is RegisterEvent.EnteredConfirmedPassword -> {
                _confirmedPasswordState.value = _confirmedPasswordState.value.copy(
                    text = event.password
                )
            }
            is RegisterEvent.EnteredPassword -> {
                _passwordState.value = _passwordState.value.copy(
                    text = event.password
                )
            }
            RegisterEvent.TogglePasswordVisibility -> {
                _passwordState.value = _passwordState.value.copy(
                    isPasswordVisible = !passwordState.value.isPasswordVisible
                )
            }
            RegisterEvent.ToggleConfirmedPasswordVisibility -> {
                _confirmedPasswordState.value = _confirmedPasswordState.value.copy(
                    isPasswordVisible = !confirmedPasswordState.value.isPasswordVisible
                )
            }
            RegisterEvent.Register -> {
                register()
            }
        }
    }

    private fun register() = viewModelScope.launch(defaultDispatcher) {
        _emailState.value = _emailState.value.copy(error = null)
        _passwordState.value = _passwordState.value.copy(error = null)
        _confirmedPasswordState.value = _confirmedPasswordState.value.copy(error = null)
        _registerState.value = RegisterState(isLoading = true)
        val registerResult = registerUseCase(
            email = emailState.value.text,
            password = passwordState.value.text,
            confirmPassword = confirmedPasswordState.value.text
        )
        if (registerResult.emailError != null) {
            _emailState.value = _emailState.value.copy(
                error = registerResult.emailError
            )
        }
        if (registerResult.passwordError != null) {
            _passwordState.value = _passwordState.value.copy(
                error = registerResult.passwordError
            )
        }
        if (registerResult.confirmPasswordError != null) {
            _confirmedPasswordState.value = _confirmedPasswordState.value.copy(
                error = registerResult.confirmPasswordError
            )
        }
        when (registerResult.result) {
            is Resource.Success -> {
                _eventFlow.emit(UiEvent.ShowSnackBar(UiText.StringResource(R.string.successfully_registered_an_account)))
                _registerState.value = RegisterState(isLoading = false)
                _emailState.value = StandardTextFieldsState()
                _passwordState.value = PasswordTextFieldState()
                _confirmedPasswordState.value = PasswordTextFieldState()
            }
            is Resource.Error -> {
                _eventFlow.emit(
                    UiEvent.ShowSnackBar(
                        registerResult.result.uiText ?: UiText.unknownError()
                    )
                )
                _registerState.value = RegisterState(false)
            }
            null -> {
                _registerState.value = RegisterState(false)
            }
        }
    }

}