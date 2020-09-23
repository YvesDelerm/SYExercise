package fr.ydelerm.sherpanyves.datasource

import fr.ydelerm.sherpanyves.model.Album
import fr.ydelerm.sherpanyves.model.Photo
import fr.ydelerm.sherpanyves.model.Post
import fr.ydelerm.sherpanyves.model.User

interface SlaveDataSource : ReadDataSource {
    fun insertUsers(users: List<User>)
    fun insertAlbums(albums: List<Album>)
    fun insertPosts(posts: List<Post>)
    fun insertPhotos(photos: List<Photo>)
}