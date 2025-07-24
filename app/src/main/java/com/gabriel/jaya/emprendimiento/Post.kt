package com.gabriel.jaya.emprendimiento

data class Post(
    val userName: String,
    val userAvatarUrl: String,
    val timeAgo: String,
    val location: String,
    val contentText: String,
    val contentImageUrl: String? = null, // La imagen es opcional
    val likes: Int,
    val comments: Int
)
