package ir.vbile.app.venture.feature_auth.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import ir.vbile.app.venture.R
import ir.vbile.app.venture.core.presentation.NavigationActions
import ir.vbile.app.venture.core.util.Screen
import ir.vbile.app.venture.core.util.TestTags
import ir.vbile.app.venture.feature_auth.util.AuthConstants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun SplashScreen(
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    vm: SplashViewModel = hiltViewModel(),
    navAction: (NavigationActions) -> Unit = {}
) {
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
            delay(AuthConstants.SPLASH_SCREEN_DURATION)
            navAction(NavigationActions.NavigateUp)
            navAction(NavigationActions.Navigate(Screen.RegisterScreen.route))
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = TestTags.SPLASH_SCREEN_LOGO,
            modifier = Modifier.scale(scale.value)
        )
    }
}
