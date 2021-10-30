package ir.vbile.app.venture.feature_post.presentation.post_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.vbile.app.venture.R
import ir.vbile.app.venture.core.presentation.NavigationActions
import ir.vbile.app.venture.core.presentation.ui.component.ActionRow
import ir.vbile.app.venture.core.presentation.ui.component.Comment
import ir.vbile.app.venture.core.presentation.ui.component.StandardToolbar
import ir.vbile.app.venture.core.presentation.ui.theme.ProfilePictureSizeMedium
import ir.vbile.app.venture.core.presentation.ui.theme.SpaceLarge
import ir.vbile.app.venture.core.presentation.ui.theme.SpaceMedium
import ir.vbile.app.venture.core.presentation.ui.theme.SpaceSmall

@Composable
fun PostDetailScreen(
    navigationActions: (NavigationActions) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StandardToolbar(
            title = {
                Text(
                    text = stringResource(id = R.string.your_feed),
                    style = MaterialTheme.typography.h6.copy(fontSize = 16.sp),
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background)
                ) {
                    Spacer(modifier = Modifier.height(SpaceLarge))
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(MaterialTheme.shapes.medium)
                                .offset(y = ProfilePictureSizeMedium / 2f)
                                .shadow(5.dp)
                                .background(MaterialTheme.colors.surface)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.post_image),
                                contentDescription = "Post Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            Column(
                                Modifier
                                    .fillMaxSize()
                                    .padding(SpaceLarge)
                            ) {
                                ActionRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    username = "وحید گروسی",
                                    onLikeClick = { isLiked ->

                                    },
                                    onUsernameClick = { username ->

                                    },
                                    onCommentClick = {

                                    },
                                    onShareClick = {

                                    }
                                )
                                Spacer(modifier = Modifier.height(SpaceSmall))
                                Text(
                                    text = "همه ما فکر می کنیم که شناخت کاملی از خودمون داریم تا با یه معضل و چالش واقعی روبرو شیم اونموقع هست که خود واقعیمون نمود پیدا می کنه و گاهاً از انجام کارهایی سر باز می زنیم که قبل تر فکر می کردیم به راحتی انجامش خواهیم داد یا شاید به دل حوادثی بریم که به شدت نسبت بهشون گارد داشتیم",
                                    style = MaterialTheme.typography.body2
                                )
                                Spacer(modifier = Modifier.height(SpaceMedium))
                                Text(
                                    text = stringResource(
                                        id = R.string.liked_by_x_people,
                                        10
                                    ),
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.body2,
                                    fontSize = 16.sp,
                                )
                            }
                        }
                        Image(
                            painter = painterResource(id = R.drawable.profile_image),
                            contentDescription = "Profile Picture",
                            modifier =
                            Modifier
                                .size(ProfilePictureSizeMedium)
                                .clip(CircleShape)
                                .align(Alignment.TopCenter)
                        )
                    }
                }
            }
            items(20) {
                Comment(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = SpaceLarge,
                            vertical = SpaceMedium
                        ),
                    comment = ir.vbile.app.venture.core.domain.models.Comment(
                        username = "وحید گروسی $it",
                        comment = "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ، و با استفاده از طراحان گرافیک است، چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است، و برای شرایط فعلی تکنولوژی مورد نیاز، و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد، کتابهای زیادی در شصت و سه درصد گذشته حال و آینده، شناخت فراوان جامعه و متخصصان را می طلبد، تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی، و فرهنگ پیشرو در زبان فارسی ایجاد کرد، در این صورت می توان امید داشت که تمام و دشواری موجود"
                    )
                )
            }
        }
    }
}