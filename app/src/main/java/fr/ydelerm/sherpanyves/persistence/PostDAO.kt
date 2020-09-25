package fr.ydelerm.sherpanyves.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.PostAndUser

@Dao
interface PostDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(Posts: List<Post>)

    @Query("SELECT * FROM post")
    fun loadAllPosts(): LiveData<List<Post>>

    @Transaction
    @Query("SELECT * FROM user")
    fun getPostsAndUsers(): LiveData<List<PostAndUser>>
}