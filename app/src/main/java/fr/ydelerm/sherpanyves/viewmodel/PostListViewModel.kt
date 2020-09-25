package fr.ydelerm.sherpanyves.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import fr.ydelerm.sherpanyves.MyApplication
import fr.ydelerm.sherpanyves.model.PostAndUser
import fr.ydelerm.sherpanyves.repository.Repository
import javax.inject.Inject

class PostListViewModel(application: Application) : AndroidViewModel(application) {
    //var posts: LiveData<PagedList<PostAndUser>>
    private val config: PagedList.Config

    /*@Inject
    lateinit var repository: Repository*/

    init {
        /*(application as MyApplication)
            .appGraph.inject(this)*/

        config = PagedList.Config.Builder()
            .setPageSize(20)
            //.setEnablePlaceholders(true)
            .build()

        //posts = LivePagedListBuilder(repository.getPostsAndUsers(), config).build()
    }
}