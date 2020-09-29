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
    lateinit var remoteDataSource: RemoteDataSource

    @Inject
    lateinit var localDataSource: LocalDataSource

    init {
        (application as MyApplication)
            .appGraph.inject(this)
    }

    override fun refreshData() {
        val usersObservable = remoteDataSource.getUsers()
            .subscribeOn(Schedulers.io())
        val postsObservable = remoteDataSource.getPosts()
            .subscribeOn(Schedulers.io())
        val albumsObservable = remoteDataSource.getAlbums()
            .subscribeOn(Schedulers.io())
        val photosObservable = remoteDataSource.getPhotos()
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
            localDataSource.insertUsers(it.users)
            localDataSource.insertPosts(it.posts)
            localDataSource.insertAlbums(it.albums)
            localDataSource.insertPhotos(it.photos)
        }
    }

    override fun getPostsAndUsers(): DataSource.Factory<Int, PostAndUser> {
        return localDataSource.getPostsWithUsers()
    }

    override fun getUserWithAlbumsAndPhotos(givenUserId: Int): LiveData<UserWithAlbumsAndPhotos?> {
        return localDataSource.getUserWithAlbumAndPhotos(givenUserId)
    }

    override fun deletePost(post: Post) {
        localDataSource.deletePost(post)
    }

}