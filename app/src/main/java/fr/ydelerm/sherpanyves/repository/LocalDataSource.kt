package fr.ydelerm.sherpanyves.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import fr.ydelerm.sherpanyves.model.*

/**
 * allow data access from/to a local source
 */
interface LocalDataSource {
    fun insertUsers(users: List<User>)
    fun insertAlbums(albums: List<Album>)
    fun insertPosts(posts: List<Post>)
    fun insertPhotos(photos: List<Photo>)
    fun getPostsWithUsers(): DataSource.Factory<Int, PostAndUser>
    fun getPostsWithUserContaining(title: String): DataSource.Factory<Int, PostAndUser>
    fun getAlbumsWithPhotos(): LiveData<List<AlbumWithPhotos>>
    fun getUserWithAlbumAndPhotos(givenUserId: Int): LiveData<UserWithAlbumsAndPhotos?>
    fun deletePost(post: Post)
}