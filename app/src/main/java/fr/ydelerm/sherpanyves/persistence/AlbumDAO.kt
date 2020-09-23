package fr.ydelerm.sherpanyves.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.ydelerm.sherpanyves.model.Album

@Dao
interface AlbumDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbums(albums: List<Album>)

    @Query("SELECT * FROM album")
    fun loadAllAlbums(): LiveData<List<Album>>
}