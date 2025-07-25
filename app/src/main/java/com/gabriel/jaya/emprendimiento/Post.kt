package com.gabriel.jaya.emprendimiento

data class Post(
    val userName: String,
    val userAvatarUrl: Int,
    val timeAgo: String,
    val location: String,
    val contentText: String,
    val contentImageUrl: Int? = null,
    val likes: Int,
    val comments: Int
)
