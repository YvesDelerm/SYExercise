package fr.ydelerm.sherpanyves.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @ColumnInfo(name = "postUserId")
    val userId: Int,
    @PrimaryKey @ColumnInfo(name = "postId")
    val id: Int,
    val title: String,
    val body: String
)