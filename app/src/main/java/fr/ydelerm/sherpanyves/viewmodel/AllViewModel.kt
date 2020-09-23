package fr.ydelerm.sherpanyves.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fr.ydelerm.sherpanyves.MyApplication
import fr.ydelerm.sherpanyves.repository.Repository
import javax.inject.Inject

class AllViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var repository: Repository

    init {
        (application as MyApplication)
            .appGraph.inject(this)
    }

    val allUsers = repository.getUsers()
    val allPosts = repository.getPosts()
    val allAlbums = repository.getAlbums()
    val allPhotos = repository.getPhotos()

    fun refreshAll() {
        repository.refreshUsers()
        repository.refreshPosts()
        repository.refreshAlbums()
        repository.refreshPhotos()
    }
}