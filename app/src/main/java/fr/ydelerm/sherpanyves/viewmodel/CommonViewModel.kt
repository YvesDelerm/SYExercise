package fr.ydelerm.sherpanyves.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import fr.ydelerm.sherpanyves.MyApplication
import fr.ydelerm.sherpanyves.R
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.UserWithAlbumsAndPhotos
import fr.ydelerm.sherpanyves.repository.Repository
import fr.ydelerm.sherpanyves.ui.Event
import javax.inject.Inject

class CommonViewModel(application: Application) : AndroidViewModel(application) {
    private val config: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(25)
        .setEnablePlaceholders(false)
        .build()

    @Inject
    lateinit var repository: Repository

    init {
        (application as MyApplication)
            .appGraph.inject(this)
    }

    val eventMessage = MutableLiveData<Event<String>>()

    val allPostsAndUsers = LivePagedListBuilder(repository.getPostsAndUsers(), config).build()

    fun refreshData() {
        repository.refreshData()
        eventMessage.postValue(Event(getApplication<MyApplication>().baseContext.getString(R.string.data_reloaded)))
    }

    fun deletePost(post: Post) {
        repository.deletePost(post)
        eventMessage.postValue(Event(getApplication<MyApplication>().baseContext.getString(R.string.post_deleted)))
    }

    fun getUserWithAlbumsAndPhotos(userId: Int): LiveData<UserWithAlbumsAndPhotos?> {
        return repository.getUserWithAlbumsAndPhotos(userId)
    }
}