package fr.ydelerm.sherpanyves.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.ydelerm.sherpanyves.model.Album
import fr.ydelerm.sherpanyves.model.AlbumWithPhotos

@Dao
interface AlbumDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbums(albums: List<Album>)

    @Query("SELECT * FROM album")
    fun loadAllAlbums(): LiveData<List<Album>>

    @Transaction
    @Query("SELECT * FROM album")
    fun getAlbumsWithPhotos(): LiveData<List<AlbumWithPhotos>>
}