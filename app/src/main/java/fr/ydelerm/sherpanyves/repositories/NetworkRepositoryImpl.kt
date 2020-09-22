package fr.ydelerm.sherpanyves.repositories

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.ydelerm.sherpanyves.model.Album
import fr.ydelerm.sherpanyves.model.Photo
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.User
import fr.ydelerm.sherpanyves.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {
    private var users = MutableLiveData<List<User>>()
    private var posts = MutableLiveData<List<Post>>()
    private var albums = MutableLiveData<List<Album>>()
    private var photos = MutableLiveData<List<Photo>>()

    private val LOGTAG = "NetworkRepositoryImp"

     override fun getUsers(): LiveData<List<User>> {
        return users
    }

    override fun refreshUsers() {
        val callback = object : Callback<List<User>> {
            override fun onResponse(@NonNull call: Call<List<User>>, @NonNull response: Response<List<User>>) {
                users.postValue(response.body()?:ArrayList())
            }

            override fun onFailure(@NonNull call: Call<List<User>>, @NonNull throwable: Throwable) {
                Log.e(LOGTAG, "error while calling webservice", throwable)
                users.postValue(ArrayList())
            }
        }

        val call = apiService.getUsers()
        call.enqueue(callback)
    }

    override fun getPosts(): LiveData<List<Post>> {
        return posts
    }

    override fun refreshPosts() {
        val callback = object : Callback<List<Post>> {
            override fun onResponse(@NonNull call: Call<List<Post>>, @NonNull response: Response<List<Post>>) {
                posts.postValue(response.body()?:ArrayList())
            }

            override fun onFailure(@NonNull call: Call<List<Post>>, @NonNull throwable: Throwable) {
                Log.e(LOGTAG, "error while calling webservice", throwable)
                posts.postValue(ArrayList())
            }
        }

        val call = apiService.getPosts()
        call.enqueue(callback)
    }

    override fun getAlbums(): LiveData<List<Album>> {
        return albums
    }

    override fun refreshAlbums() {
        val callback = object : Callback<List<Album>> {
            override fun onResponse(@NonNull call: Call<List<Album>>, @NonNull response: Response<List<Album>>) {
                albums.postValue(response.body()?:ArrayList())
            }

            override fun onFailure(@NonNull call: Call<List<Album>>, @NonNull throwable: Throwable) {
                Log.e(LOGTAG, "error while calling webservice", throwable)
                albums.postValue(ArrayList())
            }
        }

        val call = apiService.getAlbums()
        call.enqueue(callback)
    }

    override fun getPhotos(): LiveData<List<Photo>> {
        return photos
    }

    override fun refreshPhotos() {
        val callback = object : Callback<List<Photo>> {
            override fun onResponse(@NonNull call: Call<List<Photo>>, @NonNull response: Response<List<Photo>>) {
                photos.postValue(response.body()?:ArrayList())
            }

            override fun onFailure(@NonNull call: Call<List<Photo>>, @NonNull throwable: Throwable) {
                Log.e(LOGTAG, "error while calling webservice", throwable)
                photos.postValue(ArrayList())
            }
        }

        val call = apiService.getPhotos()
        call.enqueue(callback)
    }
}