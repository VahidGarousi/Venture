package ir.vbile.app.venture.feature_auth.presentation.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.venture.core.domain.use_cases.BaseUseCase
import ir.vbile.app.venture.core.util.Resource
import ir.vbile.app.venture.core.util.Screen
import ir.vbile.app.venture.core.util.SimpleResource
import ir.vbile.app.venture.core.util.UiEvent
import ir.vbile.app.venture.feature_auth.util.AuthConstants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authenticateUseCase: BaseUseCase<SimpleResource>,
    @Named("immediateMain") private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiEvent = mutableStateOf<UiEvent>(UiEvent.Idle)
    val uiState: State<UiEvent>
        get() = _uiEvent

    init {
        viewModelScope.launch(defaultDispatcher) {
            delay(AuthConstants.SPLASH_SCREEN_DURATION)
            when (authenticateUseCase()) {
                is Resource.Success -> {
                    _uiEvent.value = UiEvent.Navigate(Screen.MainFeedScreen.route)
                }
                is Resource.Error -> {
                    _uiEvent.value = UiEvent.Navigate(Screen.RegisterScreen.route)
                }
            }
        }
    }
}