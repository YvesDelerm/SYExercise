package fr.ydelerm.sherpanyves.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.room.Transaction
import fr.ydelerm.sherpanyves.MyApplication
import fr.ydelerm.sherpanyves.R
import fr.ydelerm.sherpanyves.model.GroupedData
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.PostAndUser
import fr.ydelerm.sherpanyves.model.UserWithAlbumsAndPhotos
import fr.ydelerm.sherpanyves.ui.Event
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

const val LOGTAG = "MixedRepositoryImp"

class MixedRepositoryImpl(private val application: Application) : Repository {

    @Inject
    lateinit var remoteDataSource: RemoteDataSource

    @Inject
    lateinit var localDataSource: LocalDataSource

    init {
        (application as MyApplication)
            .appGraph.inject(this)
    }

    override fun refreshData(
        eventMessage: MutableLiveData<Event<String>>,
        isRefreshing: MutableLiveData<Boolean>
    ) {
        isRefreshing.postValue(true)

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
            .subscribe({
                isRefreshing.postValue(false)
                insertAllData(it)
                eventMessage.postValue(Event(application.baseContext.getString(R.string.data_reloaded)))
            },
                {
                    isRefreshing.postValue(false)
                    Log.e(LOGTAG, "error while loading data", it)
                    eventMessage.postValue(Event(application.baseContext.getString(R.string.error_occurred)))
                }
            )
    }

    @Transaction
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

    override fun getPostsWithUserContaining(title: String): DataSource.Factory<Int, PostAndUser> {
        return localDataSource.getPostsWithUserContaining(title)
    }

    override fun getUserWithAlbumsAndPhotos(givenUserId: Int): LiveData<UserWithAlbumsAndPhotos?> {
        return localDataSource.getUserWithAlbumAndPhotos(givenUserId)
    }

    override fun deletePost(post: Post) {
        localDataSource.deletePost(post)
    }

}