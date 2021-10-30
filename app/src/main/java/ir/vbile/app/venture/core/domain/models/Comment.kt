package ir.vbile.app.venture.core.domain.models

data class Comment(
    val commentId: Int = -1,
    val username: String = "",
    val profilePictureURL: String= "",
    val timestamp: Long = System.currentTimeMillis(),
    val comment: String = "",
    val isLiked : Boolean = false,
    val likeCount: Int = 12
)
