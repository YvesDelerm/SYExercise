package fr.ydelerm.sherpanyves.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.PostAndUser
import fr.ydelerm.sherpanyves.model.UserWithAlbumsAndPhotos
import fr.ydelerm.sherpanyves.ui.Event


interface Repository {
    /**
     * refresh users, posts, albums and photos data
     * @param eventMessage object where to write the operation callback
     */
    fun refreshData(eventMessage: MutableLiveData<Event<String>>)

    /**
     * @return a [DataSource.Factory] allowing to get paged list of [PostAndUser]
     */
    fun getPostsAndUsers(): DataSource.Factory<Int, PostAndUser>

    /**
     * look for posts by the first characters of their title
     * @param title the title to look for
     * @return a [DataSource.Factory] allowing to get paged list of [PostAndUser]
     */
    fun getPostsWithUserContaining(title: String): DataSource.Factory<Int, PostAndUser>

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