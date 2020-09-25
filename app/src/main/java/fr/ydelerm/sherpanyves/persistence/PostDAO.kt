package fr.ydelerm.sherpanyves.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.PostAndUser
import androidx.paging.DataSource

@Dao
interface PostDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(Posts: List<Post>)

    @Query("SELECT * FROM post")
    fun loadAllPosts(): LiveData<List<Post>>

    @Query("SELECT post.*, user.* FROM post INNER JOIN user ON post.postUserId == user.userId")
    fun getPostsWithUser(): LiveData<List<PostAndUser>>

    @Delete
    fun deletePost(post: Post)
}