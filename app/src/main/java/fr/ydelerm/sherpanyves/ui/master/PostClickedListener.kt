package fr.ydelerm.sherpanyves.ui.master

import fr.ydelerm.sherpanyves.model.PostAndUser

interface PostClickedListener {
    fun onPostClicked(postAndUser: PostAndUser)
}
