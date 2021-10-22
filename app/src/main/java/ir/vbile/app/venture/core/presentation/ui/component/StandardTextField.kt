package ir.vbile.app.venture.core.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import ir.vbile.app.venture.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import ir.vbile.app.venture.core.presentation.ui.theme.IconSizeMedium
import ir.vbile.app.venture.core.presentation.ui.theme.SpaceSmall
import ir.vbile.app.venture.core.util.TestTags.PASSWORD_TOGGLE
import ir.vbile.app.venture.core.util.TestTags.STANDARD_TEXT_FIELD

@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    maxLength: Int = 400,
    error: String = "",
    singleLine: Boolean = true,
    maxLines: Int = 1,
    style: TextStyle = TextStyle(
        color = MaterialTheme.colors.onBackground
    ),
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    showPasswordToggle: Boolean = false,
    onPasswordToggleClick: (Boolean) -> Unit = {},
    onValueChanged: (String) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        TextField(
            value = text,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.body1
                )
            },
            onValueChange = {
                if (it.length <= maxLength) {
                    onValueChanged(it)
                }
            },
            maxLines = maxLines,
            textStyle = style,
            isError = error != "",
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            visualTransformation = if (!showPasswordToggle && isPasswordToggleDisplayed)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
            singleLine = singleLine,
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(IconSizeMedium)
                    )
                }
            } else null,
            trailingIcon = if (isPasswordToggleDisplayed) {
                {
                    IconButton(
                        onClick = {
                            onPasswordToggleClick(!showPasswordToggle)
                        },
                        modifier = Modifier.testTag(PASSWORD_TOGGLE)
                    ) {
                        Icon(
                            imageVector = if (showPasswordToggle) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            },
                            tint = Color.White,
                            contentDescription = if (showPasswordToggle) {
                                stringResource(id = R.string.password_visible_content_description)
                            } else stringResource(id = R.string.password_hidden_content_description)
                        )
                    }
                }
            } else null,
            modifier = modifier
                .fillMaxWidth()
                .testTag(STANDARD_TEXT_FIELD)
        )
        if (error.isNotEmpty()) {
            Text(
                text = error,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = SpaceSmall)
            )
        }
    }

}