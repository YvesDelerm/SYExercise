package fr.ydelerm.sherpanyves.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import fr.ydelerm.sherpanyves.R
import fr.ydelerm.sherpanyves.model.PostAndUser
import fr.ydelerm.sherpanyves.viewmodel.AllViewModel

const val TAG_DETAIL_FRAGMENT: String = "TAG_DETAIL_FRAGMENT"
class PostListActivity : AppCompatActivity(), PostClickedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        title = getString(R.string.screens_title)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PostListFragment.newInstance())
                .commitNow()
        }

        val allViewModel = ViewModelProvider(this).get(AllViewModel::class.java)
        allViewModel.refreshAll()
    }

    override fun onPostClicked(postAndUser: PostAndUser) {
        //TODO handle master / detail
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, PostDetailFragment.newInstance(postAndUser), TAG_DETAIL_FRAGMENT)
            .commitNow()
    }

    override fun onBackPressed() {
        //TODO handle master / detail
        val detailFragment = supportFragmentManager.findFragmentByTag(TAG_DETAIL_FRAGMENT)
        detailFragment?.run {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, PostListFragment.newInstance())
                .commit()
        } ?: super.onBackPressed()
    }
}