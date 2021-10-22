package ir.vbile.app.venture.feature_auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.venture.core.domain.states.PasswordTextFieldState
import ir.vbile.app.venture.core.domain.states.StandardTextFieldsState
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class LoginViewModel @Inject constructor(
    @Named("immediateMain") private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _emailState = mutableStateOf(StandardTextFieldsState())
    val emailState: State<StandardTextFieldsState> = _emailState

    private val _passwordState = mutableStateOf(PasswordTextFieldState())
    val passwordState: State<PasswordTextFieldState> = _passwordState


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

    }
}