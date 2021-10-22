package ir.vbile.app.venture.feature_auth.presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.vbile.app.venture.R
import ir.vbile.app.venture.core.presentation.ui.component.StandardTextField
import ir.vbile.app.venture.core.presentation.ui.theme.SpaceLarge
import ir.vbile.app.venture.core.util.TestTags
import ir.vbile.app.venture.core.util.TestTags.STANDARD_TEXT_FIELD
import ir.vbile.app.venture.feature_auth.presentation.util.AuthError

@Preview
@Composable
fun RegisterScreen(
    vm: RegisterViewModel = hiltViewModel()
) {
    val emailState = vm.emailState.value
    val passwordState = vm.passwordState.value
    val confirmPasswordState = vm.confirmedPasswordState.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = SpaceLarge,
                end = SpaceLarge,
                top = SpaceLarge,
                bottom = SpaceLarge
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
            StandardTextField(
                text = emailState.text,
                hint = stringResource(id = R.string.hint_email),
                onValueChanged = {
                    vm.onEvent(RegisterEvent.EnteredEmail(it))
                },
                keyboardType = KeyboardType.Email,
                modifier = Modifier.testTag(STANDARD_TEXT_FIELD)
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
            StandardTextField(
                text = passwordState.text,
                hint = stringResource(id = R.string.hint_password),
                keyboardType = KeyboardType.Password,
                onValueChanged = {
                    vm.onEvent(RegisterEvent.EnteredPassword(it))
                },
                showPasswordToggle = passwordState.isPasswordVisible,
                onPasswordToggleClick = {
                    vm.onEvent(RegisterEvent.TogglePasswordVisibility)
                },
                modifier = Modifier.testTag(
                    TestTags.PASSWORD_TOGGLE
                )
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
            StandardTextField(
                text = confirmPasswordState.text,
                hint = stringResource(id = R.string.hint_password),
                keyboardType = KeyboardType.Password,
                onValueChanged = {
                    vm.onEvent(RegisterEvent.EnteredConfirmedPassword(it))
                },
                showPasswordToggle = confirmPasswordState.isPasswordVisible,
                onPasswordToggleClick = {
                    vm.onEvent(RegisterEvent.ToggleConfirmedPasswordVisibility)
                },
                modifier = Modifier.testTag(
                    TestTags.PASSWORD_TOGGLE
                )
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
            Button(
                onClick = {
                    vm.onEvent(RegisterEvent.Register)
                },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = R.string.register)
                )
            }
        }
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colors.secondary
                    )
                ) {
                    append(stringResource(id = R.string.already_have_an_account))
                }
                append("  ")
                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(stringResource(id = R.string.sign_up))
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable {

                }
        )
    }
}