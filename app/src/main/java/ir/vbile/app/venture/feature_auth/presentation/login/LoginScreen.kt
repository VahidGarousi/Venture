package ir.vbile.app.venture.feature_auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.hilt.navigation.compose.hiltViewModel
import ir.vbile.app.venture.R
import ir.vbile.app.venture.core.presentation.NavigationActions
import ir.vbile.app.venture.core.presentation.ui.component.StandardTextField
import ir.vbile.app.venture.core.presentation.ui.theme.SpaceLarge
import ir.vbile.app.venture.core.util.TestTags
import ir.vbile.app.venture.feature_auth.presentation.util.AuthError
import ir.vbile.app.venture.feature_auth.util.AuthConstants

@Composable
fun LoginScreen(
    vm: LoginViewModel = hiltViewModel(),
    navAction: (NavigationActions) -> Unit = {}
) {
    val emailState = vm.emailState.value
    val passwordState = vm.passwordState.value

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
                    vm.onEvent(LoginEvent.EnteredEmail(it))
                },
                error = when (emailState.error) {
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    is AuthError.InvalidEmail -> stringResource(id = R.string.not_valid_email)
                    else -> ""
                },
                keyboardType = KeyboardType.Email,
                modifier = Modifier.testTag(TestTags.STANDARD_TEXT_FIELD)
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
            StandardTextField(
                text = passwordState.text,
                hint = stringResource(id = R.string.hint_password),
                keyboardType = KeyboardType.Password,
                onValueChanged = {
                    vm.onEvent(LoginEvent.EnteredPassword(it))
                },
                error = when (passwordState.error) {
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    is AuthError.InvalidPassword -> stringResource(id = R.string.invalid_password)
                    is AuthError.InputTooShort -> stringResource(
                        id = R.string.input_too_short,
                        AuthConstants.MIN_PASSWORD_LENGTH
                    )
                    is AuthError.InvalidPasswordAndConfirmPasswordDoesNotTheSame -> stringResource(
                        id = R.string.password_are_not_same
                    )
                    else -> ""
                },
                showPasswordToggle = passwordState.isPasswordVisible,
                onPasswordToggleClick = {
                    vm.onEvent(LoginEvent.TogglePasswordVisibility)
                },
                modifier = Modifier.testTag(
                    TestTags.PASSWORD_TOGGLE
                )
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
            Button(
                onClick = {
                    vm.onEvent(LoginEvent.Login)
                },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = R.string.login)
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
                    append(stringResource(id = R.string.don_not_have_an_account_yet))
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
                    navAction(NavigationActions.NavigateUp)
                }
        )
    }
}