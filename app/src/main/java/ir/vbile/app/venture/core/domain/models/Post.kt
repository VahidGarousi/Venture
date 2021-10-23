package ir.vbile.app.venture.core.domain.models

data class Post(
    val id : String,
    val username: String,
    val imageUrl: String,
    val profilePictureURL: String,
    val description: String,
    val likeCount: Int,
    val commentCount: Int
)
