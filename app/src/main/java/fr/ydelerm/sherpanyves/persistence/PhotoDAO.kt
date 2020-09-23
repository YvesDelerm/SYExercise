package fr.ydelerm.sherpanyves.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.ydelerm.sherpanyves.model.Photo

@Dao
interface PhotoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotos(Photos: List<Photo>)

    @Query("SELECT * FROM photo")
    fun loadAllPhotos(): LiveData<List<Photo>>
}