package fr.ydelerm.sherpanyves.persistence

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import fr.ydelerm.sherpanyves.model.*
import fr.ydelerm.sherpanyves.repository.SlaveDataSource
import javax.inject.Inject

class DbDataSourceImpl @Inject constructor(db: AppDatabase) : SlaveDataSource {

    private var albumDAO: AlbumDAO = db.albumDAO()
    private var photoDAO: PhotoDAO = db.photoDAO()
    private var postDAO: PostDAO = db.postDAO()
    private var userDAO: UserDAO = db.userDAO()

    override fun getUsers(): LiveData<List<User>> {
        return userDAO.loadAllUsers()
    }

    override fun getPosts(): LiveData<List<Post>> {
        return postDAO.loadAllPosts()
    }

    override fun getAlbums(): LiveData<List<Album>> {
        return albumDAO.loadAllAlbums()
    }

    override fun getPhotos(): LiveData<List<Photo>> {
        return photoDAO.loadAllPhotos()
    }

    override fun insertUsers(users: List<User>) {
        Thread { userDAO.insertUsers(users) }.start()
    }

    override fun insertAlbums(albums: List<Album>) {
        Thread { albumDAO.insertAlbums(albums) }.start()
    }

    override fun insertPosts(posts: List<Post>) {
        Thread { postDAO.insertPosts(posts) }.start()
    }

    override fun insertPhotos(photos: List<Photo>) {
        Thread { photoDAO.insertPhotos(photos) }.start()
    }

    override fun getPostsWithUsers(): LiveData<List<PostAndUser>> {
        return postDAO.getPostsWithUser()
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