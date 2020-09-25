package fr.ydelerm.sherpanyves.model

import androidx.room.Embedded
import androidx.room.Relation

data class PostAndUser(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "postUserId"
    )
    val post: Post)
