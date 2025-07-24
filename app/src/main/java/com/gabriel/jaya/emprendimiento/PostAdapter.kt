package com.gabriel.jaya.emprendimiento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gabriel.jaya.emprendimiento.R
import com.gabriel.jaya.emprendimiento.databinding.ItemPostBinding
import com.gabriel.jaya.emprendimiento.Post

class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        with(holder.binding) {
            tvUserName.text = "${post.userName} en ${post.location}"
            tvTimeAgo.text = post.timeAgo
            tvPostContent.text = post.contentText
            tvLikes.text = "${post.likes} me gusta"
            tvComments.text = "${post.comments} comentarios"

            // Cargar avatar
            Glide.with(holder.itemView.context).load(post.userAvatarUrl).circleCrop().into(ivUserAvatar)

            // Mostrar imagen del post si existe
            if (post.contentImageUrl != null) {
                ivPostImage.visibility = View.VISIBLE
                Glide.with(holder.itemView.context).load(post.contentImageUrl).into(ivPostImage)
            } else {
                ivPostImage.visibility = View.GONE
            }
        }
    }
}