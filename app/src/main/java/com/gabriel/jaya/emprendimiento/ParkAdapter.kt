package com.gabriel.jaya.emprendimiento

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.jaya.emprendimiento.databinding.ItemParkBinding

class ParkAdapter(
    private val parks: List<Park>,
    private val onAdoptClick: (Park) -> Unit,
    private val onDetailsClick: (Park) -> Unit, // <-- NUEVA FUNCIÓN LAMBDA
    private val onCardClick: (Park) -> Unit
) : RecyclerView.Adapter<ParkAdapter.ParkViewHolder>() {

    inner class ParkViewHolder(val binding: ItemParkBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkViewHolder {
        val binding = ItemParkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParkViewHolder(binding)
    }

    override fun getItemCount() = parks.size

    override fun onBindViewHolder(holder: ParkViewHolder, position: Int) {
        val park = parks[position]
        holder.binding.tvParkName.text = park.name
        holder.binding.ivParkImage.setImageResource(park.imageResId)

        // Asignar los listeners
        holder.binding.btnAdoptarPark.setOnClickListener {
            onAdoptClick(park)
        }

        // Asignar el listener al nuevo botón
        holder.binding.btnVerDetalles.setOnClickListener {
            onDetailsClick(park)
        }

        holder.itemView.setOnClickListener { // El itemView es toda la tarjeta
            onCardClick(park)
        }
    }
}