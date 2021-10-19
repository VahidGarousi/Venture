package ir.vbile.app.venture.core.presentation.ui.component

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import ir.vbile.app.venture.R
import ir.vbile.app.venture.core.domain.models.BottomNavItem

@Composable
fun StandardScaffold(
    navController: NavController,
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier,
    showBottomBar: Boolean = true,
    items: List<BottomNavItem> = bottomNavItems,
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            StandardBottomBar(navController, items, showBottomBar)
        },
        floatingActionButton = {
            if (showBottomBar) {
                FloatingActionButton(
                    backgroundColor = MaterialTheme.colors.primary,
                    onClick = onFabClick
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = stringResource(id = R.string.play_game),
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        scaffoldState = scaffoldState,
        modifier = modifier
    ) {
        content()
    }
}