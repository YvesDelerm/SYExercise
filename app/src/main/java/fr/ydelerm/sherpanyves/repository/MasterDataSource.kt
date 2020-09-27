package fr.ydelerm.sherpanyves.repository
import fr.ydelerm.sherpanyves.model.Album
import fr.ydelerm.sherpanyves.model.Photo
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.User
import io.reactivex.rxjava3.core.Observable

interface MasterDataSource {
    fun getUsers(): Observable<List<User>>
    fun getPosts(): Observable<List<Post>>
    fun getAlbums(): Observable<List<Album>>
    fun getPhotos(): Observable<List<Photo>>

}