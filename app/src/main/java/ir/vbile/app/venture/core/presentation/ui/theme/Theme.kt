package ir.vbile.app.venture.core.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow

private val DarkColorPalette = darkColors(
    primary = IrrigoPurple,
    primaryVariant = Purple700,
    secondary = IrrigoPurple,
    background = ColdSteal,
    surface = BlackVelvet
)

private val LightColorPalette = lightColors(
    primary = IrrigoPurple,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = ColdSteal,
    surface = BlackVelvet,
    /* Other default colors to override
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun VentureTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}