package fr.ydelerm.sherpanyves.repository

import androidx.lifecycle.LiveData
import fr.ydelerm.sherpanyves.model.*

interface Repository {
    fun getUsers(): LiveData<List<User>>
    fun refreshUsers()
    fun getPosts(): LiveData<List<Post>>
    fun refreshPosts()
    fun getAlbums(): LiveData<List<Album>>
    fun refreshAlbums()
    fun getPhotos(): LiveData<List<Photo>>
    fun refreshPhotos()
    fun getPostsWithUsers(): LiveData<List<PostWithUser>>
}