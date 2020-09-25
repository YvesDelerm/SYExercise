package fr.ydelerm.sherpanyves.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import fr.ydelerm.sherpanyves.model.*

interface SlaveDataSource : ReadDataSource {
    fun insertUsers(users: List<User>)
    fun insertAlbums(albums: List<Album>)
    fun insertPosts(posts: List<Post>)
    fun insertPhotos(photos: List<Photo>)
    fun getPostsWithUsers(): LiveData<List<PostAndUser>>
    fun getAlbumsWithPhotos(): LiveData<List<AlbumWithPhotos>>
    fun getUserWithAlbumAndPhotos(givenUserId: Int): LiveData<UserWithAlbumsAndPhotos?>
    fun deletePost(post: Post)
}