package fr.ydelerm.sherpanyves.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import fr.ydelerm.sherpanyves.MyApplication
import fr.ydelerm.sherpanyves.model.*
import javax.inject.Inject

class MixedRepositoryImpl(application: Application) : Repository {

    @Inject
    lateinit var masterDataSource: MasterDataSource
    @Inject
    lateinit var slaveDataSource: SlaveDataSource

    init {
        (application as MyApplication)
            .appGraph.inject(this)
        masterDataSource.getUsers().observeForever { slaveDataSource.insertUsers(it)}
        masterDataSource.getAlbums().observeForever { slaveDataSource.insertAlbums(it)}
        masterDataSource.getPhotos().observeForever { slaveDataSource.insertPhotos(it)}
        masterDataSource.getPosts().observeForever { slaveDataSource.insertPosts(it)}
    }
    override fun getUsers(): LiveData<List<User>> {
        return slaveDataSource.getUsers()
    }

    override fun refreshUsers() {
        masterDataSource.refreshUsers()
    }

    override fun getPosts(): LiveData<List<Post>> {
        return slaveDataSource.getPosts()
    }

    override fun refreshPosts() {
        masterDataSource.refreshPosts()
    }

    override fun getAlbums(): LiveData<List<Album>> {
        return slaveDataSource.getAlbums()
    }

    override fun refreshAlbums() {
        masterDataSource.refreshAlbums()
    }

    override fun getPhotos(): LiveData<List<Photo>> {
        return slaveDataSource.getPhotos()
    }

    override fun refreshPhotos() {
        masterDataSource.refreshPhotos()
    }

    override fun getPostsAndUsers(): LiveData<List<PostAndUser>> {
        return slaveDataSource.getPostsWithUsers()
    }

    override fun getAlbumsWithPhotos(): LiveData<List<AlbumWithPhotos>> {
        return slaveDataSource.getAlbumsWithPhotos()
    }

    override fun getUserWithAlbumsAndPhotos(givenUserId: Int): LiveData<UserWithAlbumsAndPhotos?> {
        return slaveDataSource.getUserWithAlbumAndPhotos(givenUserId)
    }

    override fun deletePost(post: Post) {
        slaveDataSource.deletePost(post)
    }

}