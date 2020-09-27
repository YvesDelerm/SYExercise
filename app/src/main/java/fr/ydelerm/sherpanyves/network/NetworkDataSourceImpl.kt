package fr.ydelerm.sherpanyves.network

import fr.ydelerm.sherpanyves.model.Album
import fr.ydelerm.sherpanyves.model.Photo
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.User
import fr.ydelerm.sherpanyves.repository.MasterDataSource
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    MasterDataSource {

    override fun getUsers(): Observable<List<User>> {
        return apiService.getUsers()
    }

    override fun getPosts(): Observable<List<Post>> {
        return apiService.getPosts()
    }

    override fun getAlbums(): Observable<List<Album>> {
        return apiService.getAlbums()
    }

    override fun getPhotos(): Observable<List<Photo>> {
        return apiService.getPhotos()
    }
}