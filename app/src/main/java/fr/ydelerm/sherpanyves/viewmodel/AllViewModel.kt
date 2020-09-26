package fr.ydelerm.sherpanyves.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fr.ydelerm.sherpanyves.MyApplication
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.UserWithAlbumsAndPhotos
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
    val allPostsAndUsers = repository.getPostsAndUsers()
    val allAlbumWithPhotos = repository.getAlbumsWithPhotos()

    fun refreshAll() {
        repository.refreshUsers()
        repository.refreshPosts()
        repository.refreshAlbums()
        repository.refreshPhotos()
    }

    fun deletePost(post: Post) {
        repository.deletePost(post)
    }

    fun getUserWithAlbumsAndPhotos(userId: Int): LiveData<UserWithAlbumsAndPhotos?> {
        return repository.getUserWithAlbumsAndPhotos(userId)
    }
}