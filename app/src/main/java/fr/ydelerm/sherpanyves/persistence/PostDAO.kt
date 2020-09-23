package fr.ydelerm.sherpanyves.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.ydelerm.sherpanyves.model.Post

@Dao
interface PostDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(Posts: List<Post>)

    @Query("SELECT * FROM post")
    fun loadAllPosts(): LiveData<List<Post>>
}