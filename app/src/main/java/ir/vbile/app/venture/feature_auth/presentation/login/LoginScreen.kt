package ir.vbile.app.venture.feature_auth.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import ir.vbile.app.venture.core.util.TestTags

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
            .testTag(TestTags.LOGIN_SCREEN_BOX)
    ) {
        Text(
            text = "Login",
            color = Color.White,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}