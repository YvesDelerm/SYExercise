package fr.ydelerm.sherpanyves.datasource

interface MasterDataSource : ReadDataSource {
    fun refreshUsers()
    fun refreshPosts()
    fun refreshAlbums()
    fun refreshPhotos()

}