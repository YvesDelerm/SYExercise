package fr.ydelerm.sherpanyves.ui.detail


/**
 * Callback when user clicked on a album header
 */
interface HeaderClickListener {
    fun onHeaderClick(albumItem: AlbumAdapter.AlbumItem, position: Int)
}