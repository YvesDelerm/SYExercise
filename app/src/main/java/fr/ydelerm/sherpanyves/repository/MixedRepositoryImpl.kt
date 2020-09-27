package fr.ydelerm.sherpanyves.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import fr.ydelerm.sherpanyves.MyApplication
import fr.ydelerm.sherpanyves.model.GroupedData
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.PostAndUser
import fr.ydelerm.sherpanyves.model.UserWithAlbumsAndPhotos
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MixedRepositoryImpl(application: Application) : Repository {

    @Inject
    lateinit var masterDataSource: MasterDataSource

    @Inject
    lateinit var slaveDataSource: SlaveDataSource

    init {
        (application as MyApplication)
            .appGraph.inject(this)
    }

    override fun refreshData() {
        val usersObservable = masterDataSource.getUsers()
            .subscribeOn(Schedulers.io())
        val postsObservable = masterDataSource.getPosts()
            .subscribeOn(Schedulers.io())
        val albumsObservable = masterDataSource.getAlbums()
            .subscribeOn(Schedulers.io())
        val photosObservable = masterDataSource.getPhotos()
            .subscribeOn(Schedulers.io())
        Observable.zip(
            usersObservable,
            postsObservable,
            albumsObservable,
            photosObservable,
            { users, posts, albums, photos ->
                GroupedData(users, posts, albums, photos)
            }).subscribeOn(Schedulers.io())
            .subscribe {
                insertAllData(it)
            }
    }

    private fun insertAllData(allData: GroupedData?) {
        allData?.let {
            slaveDataSource.insertUsers(it.users)
            slaveDataSource.insertPosts(it.posts)
            slaveDataSource.insertAlbums(it.albums)
            slaveDataSource.insertPhotos(it.photos)
        }
    }

    override fun getPostsAndUsers(): DataSource.Factory<Int, PostAndUser> {
        return slaveDataSource.getPostsWithUsers()
    }

    override fun getUserWithAlbumsAndPhotos(givenUserId: Int): LiveData<UserWithAlbumsAndPhotos?> {
        return slaveDataSource.getUserWithAlbumAndPhotos(givenUserId)
    }

    override fun deletePost(post: Post) {
        slaveDataSource.deletePost(post)
    }

}