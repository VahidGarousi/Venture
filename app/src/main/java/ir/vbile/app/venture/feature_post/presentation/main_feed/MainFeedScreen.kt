package ir.vbile.app.venture.feature_post.presentation.main_feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.vbile.app.venture.R
import ir.vbile.app.venture.core.presentation.NavigationActions
import ir.vbile.app.venture.core.presentation.ui.component.Post
import ir.vbile.app.venture.core.presentation.ui.component.StandardToolbar
import ir.vbile.app.venture.core.util.Screen

@Composable
fun MainFeedScreen(
    navigationActions: (NavigationActions) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            onNavigateUp = { navigationActions(NavigationActions.NavigateUp) },
            modifier = Modifier.fillMaxWidth(),
            title = {
                Text(
                    text = stringResource(id = R.string.your_feed),
                    style = MaterialTheme.typography.h6.copy(fontSize = 16.sp),
                    color = MaterialTheme.colors.onBackground
                )
            },
            navActions = {
                IconButton(onClick = {
                    navigationActions(NavigationActions.Navigate(Screen.SearchScreen.route))
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Add",
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        )
        Box(modifier = Modifier.fillMaxWidth()) {
            LazyColumn {
                items(20) {
                    Post(
                        post = ir.vbile.app.venture.core.domain.models.Post(
                            id = "1",
                            username = "وحید گروسی",
                            imageUrl = "https://vahidgarousi.ir/blog/wp-content/uploads/2020/04/Untitled-1-360x240.jpg",
                            profilePictureURL = "https://avatars.githubusercontent.com/u/19606714?v=4",
                            description = "همه ما فکر می کنیم که شناخت کاملی از خودمون داریم تا با یه معضل و چالش واقعی روبرو شیم اونموقع هست که خود واقعیمون نمود پیدا می کنه و گاهاً از انجام کارهایی سر باز می زنیم که قبل تر فکر می کردیم به راحتی انجامش خواهیم داد یا شاید به دل حوادثی بریم که به شدت نسبت بهشون گارد داشتیم",
                            likeCount = 123,
                            commentCount = 17
                        ),
                        onPostClick = {
                            navigationActions(NavigationActions.Navigate(it.id))
                        }
                    )
                }
            }
        }
    }
}