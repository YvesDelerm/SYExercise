package fr.ydelerm.sherpanyves.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
    (
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("userId"),
        childColumns = arrayOf("albumUserId"),
        onDelete = ForeignKey.NO_ACTION
    )]
)
data class Album(
    @ColumnInfo(name = "albumUserId")
    val userId: Int,
    @PrimaryKey @ColumnInfo(name = "albumId")
    val id: Int,
    val title: String
)