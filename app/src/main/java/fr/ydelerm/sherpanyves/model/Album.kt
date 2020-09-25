package fr.ydelerm.sherpanyves.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album(
    @ColumnInfo(name = "albumUserId")
    val userId: Int,
    @PrimaryKey @ColumnInfo(name = "albumId")
    val id: Int,
    val title: String
)