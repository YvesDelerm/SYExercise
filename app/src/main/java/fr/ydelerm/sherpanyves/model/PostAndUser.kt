package fr.ydelerm.sherpanyves.model

import androidx.room.Embedded
import java.io.Serializable

data class PostAndUser(
    @Embedded val user: User,
    @Embedded val post: Post) : Serializable
