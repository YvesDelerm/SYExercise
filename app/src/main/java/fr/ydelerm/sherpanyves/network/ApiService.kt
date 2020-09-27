package fr.ydelerm.sherpanyves.network

import androidx.annotation.NonNull
import fr.ydelerm.sherpanyves.model.Album
import fr.ydelerm.sherpanyves.model.Photo
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.User
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {
    @NonNull
    @GET("posts")
    fun getPosts(): Observable<List<Post>>

    @NonNull
    @GET("users")
    fun getUsers(): Observable<List<User>>

    @NonNull
    @GET("albums")
    fun getAlbums(): Observable<List<Album>>

    @NonNull
    @GET("photos")
    fun getPhotos(): Observable<List<Photo>>
}