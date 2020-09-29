package fr.ydelerm.sherpanyves.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.PostAndUser
import fr.ydelerm.sherpanyves.model.UserWithAlbumsAndPhotos


interface Repository {
    /**
     * refresh users, posts, albums and photos data
     */
    fun refreshData()

    /**
     * @return a [DataSource.Factory] allowing to get paged list of [PostAndUser]
     */
    fun getPostsAndUsers(): DataSource.Factory<Int, PostAndUser>


    /**
     * retrieve a user with its albums and photos
     * @param givenUserId the id of requested user
     * @return the user with its albums and photos
     */
    fun getUserWithAlbumsAndPhotos(givenUserId: Int): LiveData<UserWithAlbumsAndPhotos?>

    /**
     * Delete post from local data source
     * @param post the post to delete
     */
    fun deletePost(post: Post)
}