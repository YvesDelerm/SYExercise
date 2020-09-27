package fr.ydelerm.sherpanyves.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.PostAndUser
import fr.ydelerm.sherpanyves.model.UserWithAlbumsAndPhotos

interface Repository {
    fun refreshData()
    fun getPostsAndUsers(): DataSource.Factory<Int, PostAndUser>
    fun getUserWithAlbumsAndPhotos(givenUserId: Int): LiveData<UserWithAlbumsAndPhotos?>
    fun deletePost(post: Post)
}