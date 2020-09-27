package fr.ydelerm.sherpanyves.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
    (
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("userId"),
        childColumns = arrayOf("postUserId"),
        onDelete = ForeignKey.NO_ACTION
    )]
)
data class Post(
    @ColumnInfo(name = "postUserId")
    val userId: Int,
    @PrimaryKey @ColumnInfo(name = "postId")
    val id: Int,
    val title: String,
    val body: String
) : Serializable