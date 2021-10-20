package ir.vbile.app.venture.feature_auth.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.venture.core.domain.states.PasswordTextFieldState
import ir.vbile.app.venture.core.domain.states.StandardTextFieldsState
import ir.vbile.app.venture.core.util.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(

) : ViewModel() {

    private val _emailState = mutableStateOf(StandardTextFieldsState())
    val emailState: State<StandardTextFieldsState> = _emailState


    private val _passwordState = mutableStateOf(PasswordTextFieldState())
    val passwordState: State<PasswordTextFieldState> = _passwordState

    private val _confirmedPasswordState = mutableStateOf(PasswordTextFieldState())
    val confirmedPasswordState: State<PasswordTextFieldState> = _confirmedPasswordState

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

    private fun register() {

    }

}