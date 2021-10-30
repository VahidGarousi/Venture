package ir.vbile.app.venture.core.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ir.vbile.app.venture.R

val yekanBakh = FontFamily(
    listOf(
        Font(R.font.yekan_bakh_regular, FontWeight.Normal),
        Font(R.font.yekan_bakh_medium, FontWeight.Medium),
        Font(R.font.yekan_bakh_bold, FontWeight.Bold),
        Font(R.font.yekan_bakh_heavy, FontWeight.Black)
    )
)


// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = yekanBakh,
    body1 = TextStyle(
        fontFamily = yekanBakh,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = yekanBakh,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = TextWhite
    ),
    h1 = TextStyle(
        fontFamily = yekanBakh,
        fontWeight = FontWeight.Black,
        fontSize = 30.sp,
        color = TextWhite
    ),
    h2 = TextStyle(
        fontFamily = yekanBakh,
        fontWeight = FontWeight.Black,
        fontSize = 24.sp,
        color = TextWhite
    ),
    button = TextStyle(
        fontFamily = yekanBakh,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        color = TextWhite,
        shadow = Shadow(Color.LightGray, Offset(0.2f, 0.2f), 0.5f)
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        letterSpacing = 0.1.sp
    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)