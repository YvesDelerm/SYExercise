package fr.ydelerm.sherpanyves.datasource

import androidx.lifecycle.LiveData
import fr.ydelerm.sherpanyves.model.Album
import fr.ydelerm.sherpanyves.model.Photo
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.User

interface ReadDataSource {
    fun getUsers(): LiveData<List<User>>
    fun getPosts(): LiveData<List<Post>>
    fun getAlbums(): LiveData<List<Album>>
    fun getPhotos(): LiveData<List<Photo>>
}