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

/**
 * [AndroidViewModel] for master and detail
 */
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

    /**
     * Event allowing to inform user
     */
    val eventMessage = MutableLiveData<Event<String>>()

    /**
     * gives paged posts and users data
     */
    var postsAndUsers = LivePagedListBuilder(repository.getPostsAndUsers(), config).build()

    /**
     * indicates when data refresh is ongoing
     */
    var isRefreshing = MutableLiveData(false)

    /**
     * Enable / disable post filtering by title
     * An empty string disables filtering
     * @param filterText the text to look for in posts title
     */
    fun filterPosts(filterText: String?) {
        isFiteringEnabled.value = !(filterText.isNullOrEmpty())
        val factory =
            if (filterText.isNullOrEmpty()) repository.getPostsAndUsers() else repository.getPostsWithUserContaining(
                "%$filterText%"
            )
        postsAndUsers = LivePagedListBuilder(factory, config).build()
    }

    val isFiteringEnabled = MutableLiveData(false)

    /**
     * refresh users, posts, albums and photos data
     *
     */
    fun refreshData() {
        repository.refreshData(eventMessage, isRefreshing)
    }

    /**
     * Delete post
     * @param post the post to delete
     */
    fun deletePost(post: Post) {
        repository.deletePost(post)
        eventMessage.postValue(Event(getApplication<MyApplication>().baseContext.getString(R.string.post_deleted)))
    }

    /**
     * retrieve a user with its albums and photos
     * @param userId the id of requested user
     * @return the user with its albums and photos
     */
    fun getUserWithAlbumsAndPhotos(userId: Int): LiveData<UserWithAlbumsAndPhotos?> {
        return repository.getUserWithAlbumsAndPhotos(userId)
    }
}