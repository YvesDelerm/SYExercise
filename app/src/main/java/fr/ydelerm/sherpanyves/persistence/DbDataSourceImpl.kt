package fr.ydelerm.sherpanyves.persistence

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import fr.ydelerm.sherpanyves.model.*
import fr.ydelerm.sherpanyves.repository.LocalDataSource
import javax.inject.Inject

class DbDataSourceImpl @Inject constructor(db: AppDatabase) : LocalDataSource {

    private var albumDAO: AlbumDAO = db.albumDAO()
    private var photoDAO: PhotoDAO = db.photoDAO()
    private var postDAO: PostDAO = db.postDAO()
    private var userDAO: UserDAO = db.userDAO()

    override fun insertUsers(users: List<User>) {
        userDAO.insertUsers(users)
    }

    override fun insertAlbums(albums: List<Album>) {
        albumDAO.insertAlbums(albums)
    }

    override fun insertPosts(posts: List<Post>) {
        postDAO.insertPosts(posts)
    }

    override fun insertPhotos(photos: List<Photo>) {
        photoDAO.insertPhotos(photos)
    }

    override fun getPostsWithUsers(): DataSource.Factory<Int, PostAndUser> {
        return postDAO.getPostsWithUser()
    }

    override fun getPostsWithUserContaining(title: String): DataSource.Factory<Int, PostAndUser> {
        return postDAO.getPostsWithUserContaining(title)
    }

    override fun getAlbumsWithPhotos(): LiveData<List<AlbumWithPhotos>> {
        return albumDAO.getAlbumsWithPhotos()
    }

    override fun getUserWithAlbumAndPhotos(givenUserId: Int): LiveData<UserWithAlbumsAndPhotos?> {
        return userDAO.loadUserWithAlbumsAndPhotos(givenUserId)
    }

    override fun deletePost(post: Post) {
        Thread { postDAO.deletePost(post) }.start()

    }
}