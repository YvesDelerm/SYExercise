package fr.ydelerm.sherpanyves.model

import androidx.room.Embedded
import androidx.room.Relation

data class AlbumWithPhotos(
    @Embedded val album: Album,
    @Relation(
        parentColumn = "albumId",
        entityColumn = "photoAlbumId"
    )
    val photos: List<Photo>
)