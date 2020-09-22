package fr.ydelerm.sherpanyves.network

import androidx.annotation.NonNull
import fr.ydelerm.sherpanyves.model.Album
import fr.ydelerm.sherpanyves.model.Photo
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @NonNull
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @NonNull
    @GET("users")
    fun getUsers(): Call<List<User>>

    @NonNull
    @GET("albums")
    fun getAlbums(): Call<List<Album>>

    @NonNull
    @GET("photos")
    fun getPhotos(): Call<List<Photo>>
}