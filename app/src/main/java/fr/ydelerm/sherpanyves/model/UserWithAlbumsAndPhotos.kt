package fr.ydelerm.sherpanyves.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithAlbumsAndPhotos(
    @Embedded val user: User,
    @Relation(
        entity = Album::class,
        parentColumn = "userId",
        entityColumn = "albumUserId"
    )
    val albumsWithPhotos: List<AlbumWithPhotos>
)