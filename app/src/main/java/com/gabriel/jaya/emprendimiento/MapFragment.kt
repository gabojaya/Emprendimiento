package com.gabriel.jaya.emprendimiento

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gabriel.jaya.emprendimiento.databinding.FragmentMapBinding
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker

class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var parksList: List<Park>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        val ctx = requireActivity().applicationContext
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Cargar los datos de los parques
        loadParks()

        // 2. Configurar el mapa con todos los marcadores
        setupMap()

        // 3. Configurar el RecyclerView
        setupRecyclerView()
    }

    private fun loadParks() {
        // Datos de ejemplo con coordenadas reales de Quito
        parksList = listOf(
            Park("1", "Parque La Carolina", R.drawable.parque_la_carolina, GeoPoint(-0.1795, -78.4834)),
            Park("2", "Parque de los Tubos", R.drawable.parque_los_tubos, GeoPoint(-0.2985, -78.5202)),
            Park("3", "Parque Metropolitano", R.drawable.parque_metropolitano, GeoPoint(-0.1983, -78.4616))
        )
        // Nota: Añade las imágenes parque_la_carolina.jpg y parque_metropolitano.jpg a res/drawable
    }

    private fun setupMap() {
        val mapView = binding.mapView
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)

        val mapController = mapView.controller
        mapController.setZoom(13.5)
        // Centramos el mapa en el primer parque de la lista
        mapController.setCenter(parksList.first().location)

        // Añadir un marcador para cada parque
        parksList.forEach { park ->
            val parkMarker = Marker(mapView)
            parkMarker.position = park.location
            parkMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            parkMarker.title = park.name

            // Cuando se hace clic en un marcador, podemos centrar el mapa en él
            parkMarker.setOnMarkerClickListener { marker, _ ->
                mapController.animateTo(marker.position)
                // Aquí podrías hacer que el RecyclerView se desplace al parque correspondiente
                true
            }
            mapView.overlays.add(parkMarker)
        }
        mapView.invalidate()
    }

    private fun setupRecyclerView() {
        val parkAdapter = ParkAdapter(
            parks = parksList,
            onAdoptClick = { park ->
                // Cuando se hace clic en "Adoptar", navegamos al detalle de ese parque
                findNavController().navigate(R.id.action_global_treeDetailFragment)
            },
            onDetailsClick = { park -> // <-- IMPLEMENTACIÓN DE LA NUEVA ACCIÓN
                // Cuando se hace clic en "Ver Detalles", hacemos LO MISMO por ahora
                findNavController().navigate(R.id.action_global_parkDetailFragment)
            },
            onCardClick = { park ->
                // Cuando se hace clic en la tarjeta, movemos el mapa a la ubicación del parque
                binding.mapView.controller.animateTo(park.location)
            }
        )
        binding.rvParks.adapter = parkAdapter
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}