package fr.ydelerm.sherpanyves.model

import androidx.room.Embedded

data class PostAndUser(
    @Embedded val user: User,
    @Embedded val post: Post)
