package ir.vbile.app.venture.core.domain.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomNavItem(
    val route: String,
    @DrawableRes val icon: Int? = null,
    @StringRes val contentDescription: Int? = null,
    val alertCount: Int? = null,
)
