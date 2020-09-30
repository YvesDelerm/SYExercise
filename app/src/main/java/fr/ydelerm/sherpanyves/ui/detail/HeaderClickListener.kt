package fr.ydelerm.sherpanyves.ui.detail


/**
 * Callback when user clicked on a album header
 */
interface HeaderClickListener {
    /**
     * @param position the adapter position of the clicked item
     * @return true if the header is now collapsed, false if it is not, null if there is no header
     */
    fun onHeaderClick(position: Int): Boolean?
}