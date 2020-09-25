package fr.ydelerm.sherpanyves.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    @ColumnInfo(name = "photoAlbumId")
    val albumId: Int,
    @PrimaryKey @ColumnInfo(name = "photoId")
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)