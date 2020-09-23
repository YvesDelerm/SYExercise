package fr.ydelerm.sherpanyves.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.ydelerm.sherpanyves.model.Album
import fr.ydelerm.sherpanyves.model.Photo
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.User

@Database(entities = [User::class, Post::class, Photo::class, Album::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO
    abstract fun postDAO(): PostDAO
    abstract fun photoDAO(): PhotoDAO
    abstract fun albumDAO(): AlbumDAO

}