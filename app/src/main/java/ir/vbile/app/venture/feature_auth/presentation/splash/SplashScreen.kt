package ir.vbile.app.venture.feature_auth.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ir.vbile.app.venture.R
import ir.vbile.app.venture.core.presentation.NavigationActions
import ir.vbile.app.venture.core.presentation.ui.theme.SpaceMedium
import ir.vbile.app.venture.core.util.TestTags
import ir.vbile.app.venture.core.util.UiEvent
import ir.vbile.app.venture.core.util.asString
import ir.vbile.app.venture.feature_auth.util.AuthConstants
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import javax.inject.Named

@Composable
fun SplashScreen(
    scaffoldState: ScaffoldState,
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    vm: SplashViewModel = hiltViewModel(),
    navAction: (NavigationActions) -> Unit = {}
) {
    val uiEvent = vm.uiState.value
    val context = LocalContext.current
    val scale = remember {
        Animatable(0f)
    }
    val overshootInterpolator = remember {
        OvershootInterpolator(3f)
    }
    LaunchedEffect(true) {
        withContext(dispatcher) {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = {
                        overshootInterpolator.getInterpolation(it)
                    }
                )
            )
        }
    }
    LaunchedEffect(uiEvent) {
        withContext(dispatcher){
            when (uiEvent) {
                is UiEvent.Navigate -> {
                    navAction(NavigationActions.NavigateUp)
                    navAction(NavigationActions.Navigate(uiEvent.route))
                }
                UiEvent.NavigateUp -> {
                    navAction(NavigationActions.NavigateUp)
                }
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.uiText.asString(
                            context
                        )
                    )
                }
                UiEvent.Idle -> Unit
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = TestTags.SPLASH_SCREEN_LOGO,
                modifier = Modifier.scale(scale.value)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h2
            )
        }
    }
}
