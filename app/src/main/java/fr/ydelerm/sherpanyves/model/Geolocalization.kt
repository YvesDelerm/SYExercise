package fr.ydelerm.sherpanyves.model

import java.io.Serializable

data class Geolocalization(
    val lat: String,
    val lng: String
) : Serializable