package fr.ydelerm.sherpanyves.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.ydelerm.sherpanyves.model.AlbumWithPhotos
import fr.ydelerm.sherpanyves.model.Photo

@Dao
interface PhotoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotos(Photos: List<Photo>)

    @Query("SELECT * FROM photo")
    fun loadAllPhotos(): LiveData<List<Photo>>
}