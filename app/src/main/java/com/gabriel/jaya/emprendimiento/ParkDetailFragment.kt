package com.gabriel.jaya.emprendimiento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gabriel.jaya.emprendimiento.R
import com.gabriel.jaya.emprendimiento.TreeAdapter
import com.gabriel.jaya.emprendimiento.databinding.FragmentParkDetailBinding
import com.gabriel.jaya.emprendimiento.Tree

class ParkDetailFragment : Fragment() {

    private var _binding: FragmentParkDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentParkDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar la Toolbar para tener la flecha de regreso
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp() // Acción de volver atrás
        }
        binding.collapsingToolbar.title = " " // Título se muestra al colapsar

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Datos de ejemplo
        val dummyTrees = listOf(
            Tree("1", "Árbol 1", "Categoría • árboles frutales", "Especificaciones del árbol", "url_de_imagen1"),
            Tree("2", "Árbol 2", "Categoría • árboles de hoja perenne", "Especificaciones del árbol", "url_de_imagen2"),
            Tree("3", "Árbol 3", "Categoría • árboles de coníferas", "Especificaciones del árbol", "url_de_imagen3")
        )

        val treeAdapter = TreeAdapter(dummyTrees) { tree ->
            // Acción al hacer clic en un árbol: navegar al detalle del árbol
            findNavController().navigate(R.id.action_global_treeDetailFragment)
        }

        binding.rvTrees.adapter = treeAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}