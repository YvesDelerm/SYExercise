package fr.ydelerm.sherpanyves.repository

interface MasterDataSource : ReadDataSource {
    fun refreshUsers()
    fun refreshPosts()
    fun refreshAlbums()
    fun refreshPhotos()

}