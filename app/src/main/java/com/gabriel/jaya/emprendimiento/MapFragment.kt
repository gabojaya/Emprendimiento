package com.gabriel.jaya.emprendimiento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gabriel.jaya.emprendimiento.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var map: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val navigateToParkDetail = {
            findNavController().navigate(R.id.action_global_parkDetailFragment)
        }

        binding.bottomSheet.setOnClickListener {
            // Navegar a la pantalla de detalle del parque
            navigateToParkDetail()
        }
        binding.btnAdoptar.setOnClickListener {
            findNavController().navigate(R.id.action_global_treeDetailFragment)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        // Posicionar el mapa en Quito y agregar marcadores
        val quito = LatLng(-0.180653, -78.467834)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(quito, 12f))
        map.addMarker(MarkerOptions().position(quito).title("Centro de Quito"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}