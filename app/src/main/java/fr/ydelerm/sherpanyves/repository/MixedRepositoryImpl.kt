package fr.ydelerm.sherpanyves.repository

import android.app.Application
import androidx.lifecycle.LiveData
import fr.ydelerm.sherpanyves.MyApplication
import fr.ydelerm.sherpanyves.datasource.MasterDataSource
import fr.ydelerm.sherpanyves.datasource.SlaveDataSource
import fr.ydelerm.sherpanyves.model.Album
import fr.ydelerm.sherpanyves.model.Photo
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.User
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

}