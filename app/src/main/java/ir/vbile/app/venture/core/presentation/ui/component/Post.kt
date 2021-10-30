package ir.vbile.app.venture.core.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import ir.vbile.app.venture.R
import ir.vbile.app.venture.core.domain.models.Post
import ir.vbile.app.venture.core.presentation.ui.theme.*
import ir.vbile.app.venture.core.util.CoreConstants

@Composable
fun Post(
    post: Post,
    modifier: Modifier = Modifier,
    showProfileImage: Boolean = true,
    onPostClick: (Post) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(SpaceMedium)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .shadow(5.dp)
                .background(Charade)
                .clickable {
                    onPostClick(post)
                }
        ) {
            Image(
                painter = rememberImagePainter(
                    data = post.imageUrl,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = "Post Image"
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpaceMedium)
            ) {
                ActionRow(
                    modifier = Modifier.fillMaxWidth(),
                    username = post.username,
                    profileImageUrl = post.profilePictureURL,
                    onCommentClick = {

                    },
                    onLikeClick = { isLiked ->

                    },
                    onShareClick = {

                    },
                    onUsernameClick = { username ->

                    }
                )
                Spacer(modifier = Modifier.height(SpaceSmall))
                SelectionContainer {
                    Text(
                        text = buildAnnotatedString {
                            append(post.description)
                            withStyle(
                                SpanStyle(
                                    color = HintGray
                                )
                            ) {
                                append(stringResource(id = R.string.read_more))
                            }
                        },
                        style = MaterialTheme.typography.body2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = CoreConstants.MAX_POST_DESCRIPTION_LINES,
                        textAlign = TextAlign.Justify
                    )
                }
                Spacer(modifier = Modifier.height(SpaceMedium))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.liked_by_x_people, post.likeCount),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body2.copy(fontSize = 14.sp)
                    )
                    Text(
                        text = stringResource(id = R.string.x_comments, post.commentCount),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body2.copy(fontSize = 14.sp)
                    )
                }
            }
        }
    }
}

@Composable
fun ActionRow(
    modifier: Modifier = Modifier,
    onLikeClick: (Boolean) -> Unit = {},
    onShareClick: () -> Unit = {},
    onCommentClick: () -> Unit = {},
    onUsernameClick: (String) -> Unit = {},
    isLiked: Boolean = false,
    username: String,
    profileImageUrl: String? = null
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (profileImageUrl != null){
                Image(
                    painter = painterResource(id = R.drawable.profile_image),
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(ProfilePictureSizeExtraSmall)
                        .clickable {
                            onUsernameClick(username)
                        },
                    contentDescription = profileImageUrl
                )
            }
            Spacer(modifier = Modifier.width(SpaceSmall))
            Text(
                text = username,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.clickable {
                    onUsernameClick(username)
                }
            )
        }
        EngagementButtons(
            isLiked = isLiked,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick,
            onShareClick = onShareClick
        )
    }
}

@Composable
fun EngagementButtons(
    modifier: Modifier = Modifier,
    iconSize: Dp = 30.dp,
    isLiked: Boolean,
    onLikeClick: (Boolean) -> Unit,
    onCommentClick: () -> Unit,
    onShareClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        IconButton(
            onClick = { onLikeClick(!isLiked) },
            modifier = Modifier.size(iconSize)
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                tint = if (isLiked) {
                    Color.Red
                } else {
                    TextWhite
                },
                contentDescription = if (isLiked) {
                    stringResource(id = R.string.unlike)
                } else
                    stringResource(id = R.string.like)
            )
        }
        Spacer(modifier = Modifier.width(SpaceMedium))
        IconButton(
            onClick = { onCommentClick() },
            modifier = Modifier.size(iconSize)
        ) {
            Icon(
                imageVector = Icons.Filled.Comment,
                contentDescription = stringResource(id = R.string.comment)
            )
        }
        Spacer(modifier = Modifier.width(SpaceMedium))
        IconButton(
            onClick = { onShareClick() },
            modifier = Modifier.size(iconSize)
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = stringResource(id = R.string.share)
            )
        }
    }
}
