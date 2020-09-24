package fr.ydelerm.sherpanyves.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.ydelerm.sherpanyves.R

class PostListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_list_activity)
        title = getString(R.string.screens_title)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PostListFragment.newInstance())
                .commitNow()
        }
    }
}