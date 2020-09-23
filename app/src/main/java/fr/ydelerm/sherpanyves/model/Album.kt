package fr.ydelerm.sherpanyves.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album(
    val userId: Int,
    @PrimaryKey
    val id: Int,
    val title: String
)