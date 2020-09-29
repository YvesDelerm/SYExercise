package fr.ydelerm.sherpanyves.ui.master

import fr.ydelerm.sherpanyves.model.PostAndUser

/**
 * Callback when user clicked on a post
 */
interface PostClickedListener {
    fun onPostClicked(postAndUser: PostAndUser)
}
