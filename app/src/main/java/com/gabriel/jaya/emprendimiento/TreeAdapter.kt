package com.gabriel.jaya.emprendimiento

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gabriel.jaya.emprendimiento.databinding.ItemTreeBinding

class TreeAdapter(private val trees: List<Tree>, param: (Any) -> Unit) : RecyclerView.Adapter<TreeAdapter.TreeViewHolder>() {

    inner class TreeViewHolder(val binding: ItemTreeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeViewHolder {
        val binding = ItemTreeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TreeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TreeViewHolder, position: Int) {
        val tree = trees[position]
        holder.binding.tvTreeName.text = tree.name
        holder.binding.tvTreeCategory.text = tree.category
        Glide.with(holder.itemView.context).load(tree.imageUrl).into(holder.binding.ivTree)

        // Aquí puedes añadir un listener para el clic en el item
    }

    override fun getItemCount() = trees.size
}