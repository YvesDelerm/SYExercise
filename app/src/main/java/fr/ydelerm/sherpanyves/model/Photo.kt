package fr.ydelerm.sherpanyves.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    val albumId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)