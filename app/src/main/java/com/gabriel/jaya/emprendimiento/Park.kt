package com.gabriel.jaya.emprendimiento

import org.osmdroid.util.GeoPoint

data class Park(
    val id: String,
    val name: String,
    val imageResId: Int,
    val location: GeoPoint
)
