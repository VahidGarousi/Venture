package ir.vbile.app.venture.feature_auth.presentation.login

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.venture.core.domain.states.PasswordTextFieldState
import ir.vbile.app.venture.core.domain.states.StandardTextFieldsState
import ir.vbile.app.venture.core.util.Resource
import ir.vbile.app.venture.core.util.Screen
import ir.vbile.app.venture.core.util.UiEvent
import ir.vbile.app.venture.core.util.UiText
import ir.vbile.app.venture.feature_auth.domain.use_case.LoginUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class LoginViewModel @Inject constructor(
    @Named("immediateMain") private val defaultDispatcher: CoroutineDispatcher,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _emailState = mutableStateOf(StandardTextFieldsState())
    val emailState: State<StandardTextFieldsState> = _emailState

    private val _passwordState = mutableStateOf(PasswordTextFieldState())
    val passwordState: State<PasswordTextFieldState> = _passwordState

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                _emailState.value = _emailState.value.copy(
                    text = event.email
                )
            }
            is LoginEvent.EnteredPassword -> {
                _passwordState.value = _passwordState.value.copy(
                    text = event.password
                )
            }
            LoginEvent.TogglePasswordVisibility -> {
                _passwordState.value = _passwordState.value.copy(
                    isPasswordVisible = !_passwordState.value.isPasswordVisible
                )
            }
            LoginEvent.Login -> login()
        }
    }

    private fun login() {
        viewModelScope.launch(defaultDispatcher) {
            _loginState.value = _loginState.value.copy(
                isLoading = true
            )
            val result = loginUseCase(
                email = emailState.value.text,
                password = passwordState.value.text
            )
            _loginState.value = _loginState.value.copy(
                isLoading = false
            )
            if (result.emailError != null) {
                _emailState.value = _emailState.value.copy(
                    error = result.emailError
                )
            }
            if (result.passwordError != null) {
                _passwordState.value = _passwordState.value.copy(
                    error = result.passwordError
                )
            }
            when (result.result) {
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.ShowSnackBar(
                            result.result.uiText ?: UiText.unknownError()
                        )
                    )
                }
                is Resource.Success -> {
                    _eventFlow.emit(UiEvent.Navigate(Screen.MainFeedScreen.route))
                }
            }
        }
    }
}