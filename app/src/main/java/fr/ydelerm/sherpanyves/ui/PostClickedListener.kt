package fr.ydelerm.sherpanyves.ui

import fr.ydelerm.sherpanyves.model.PostAndUser

interface PostClickedListener {
    fun onPostClicked(postAndUser: PostAndUser)
}
