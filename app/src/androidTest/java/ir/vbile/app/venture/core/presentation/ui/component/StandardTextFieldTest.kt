package ir.vbile.app.venture.core.presentation.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.text.input.KeyboardType
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import ir.vbile.app.venture.core.domain.states.PasswordTextFieldState
import ir.vbile.app.venture.core.presentation.ui.theme.VentureTheme
import ir.vbile.app.venture.core.util.TestTags.PASSWORD_TOGGLE
import ir.vbile.app.venture.core.util.TestTags.STANDARD_TEXT_FIELD
import ir.vbile.app.venture.platform.mobile.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StandardTextFieldTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun enterTooLongString_maxLengthNotExceeded() {
        composeTestRule.setContent {
            var text by remember {
                mutableStateOf("")
            }
            VentureTheme {
                StandardTextField(
                    text = text,
                    maxLength = 5,
                    onValueChanged = { text = it }
                )
            }
        }
        val expectedValue = "aaaaa"
        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextClearance()
        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextInput(expectedValue)
        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextInput("a")
        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .assertTextEquals(expectedValue)
    }

    @Test
    fun enterPassword_toggleVisibility_passwordVisible() {
        var passwordState by mutableStateOf(PasswordTextFieldState())
        composeTestRule.setContent {
            VentureTheme {
                StandardTextField(
                    text = passwordState.text,
                    onValueChanged = {
                        passwordState = passwordState.copy(
                            text = it
                        )
                    },
                    maxLength = 5,
                    keyboardType = KeyboardType.Password,
                    onPasswordToggleClick = {
                        passwordState = passwordState.copy(
                            isPasswordVisible = !passwordState.isPasswordVisible
                        )
                    },
                    showPasswordToggle = passwordState.isPasswordVisible
                )
            }
        }
        composeTestRule
            .onNodeWithTag(PASSWORD_TOGGLE)
            .performClick()
        assertThat(passwordState.isPasswordVisible).isTrue()
    }

    @Test
    fun enterPassword_toggleVisibility_passwordVisibleAndEqualsToExpectedPassword() {
        var passwordState by mutableStateOf(PasswordTextFieldState())
        val enteredPassword = "aaaaa"
        composeTestRule.setContent {
            VentureTheme {
                StandardTextField(
                    text = passwordState.text,
                    onValueChanged = {
                        passwordState = passwordState.copy(
                            text = it
                        )
                    },
                    maxLength = 5,
                    keyboardType = KeyboardType.Password,
                    onPasswordToggleClick = {
                        passwordState = passwordState.copy(
                            isPasswordVisible = !passwordState.isPasswordVisible
                        )
                    },
                    showPasswordToggle = passwordState.isPasswordVisible
                )
            }
        }
        composeTestRule
            .onNodeWithTag(PASSWORD_TOGGLE)
            .performClick()
        assertThat(passwordState.isPasswordVisible).isTrue()

        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextClearance()


        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextInput(enteredPassword)

        assertThat(passwordState.text).isEqualTo(enteredPassword)
    }
}