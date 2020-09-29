package fr.ydelerm.sherpanyves.ui

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import fr.ydelerm.sherpanyves.R
import fr.ydelerm.sherpanyves.model.PostAndUser
import fr.ydelerm.sherpanyves.ui.detail.PostDetailFragment
import fr.ydelerm.sherpanyves.ui.master.PostClickedListener
import fr.ydelerm.sherpanyves.ui.master.PostListFragment
import fr.ydelerm.sherpanyves.viewmodel.CommonViewModel

const val PARAM_SELECTED_POST = "SELECTED_POST"
const val TAG_DETAIL_FRAGMENT = "TAG_DETAIL_FRAGMENT"
const val TAG_MASTER_FRAGMENT = "TAG_MASTER_FRAGMENT"

/**
 * MainActivity : a [AppCompatActivity] that displays master (posts list) and detail (post detail) views
 */
class MainActivity : AppCompatActivity(), PostClickedListener {

    private var selectedPost: PostAndUser? = null

    var hasSecondPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        title = getString(R.string.screens_title)
        this.actionBar?.setDisplayHomeAsUpEnabled(true)
        hasSecondPane = (findViewById<FrameLayout>(R.id.detail_fragment) != null)

        val commonViewModel = ViewModelProvider(this).get(CommonViewModel::class.java)

        displayList()
        if (savedInstanceState == null) {
            commonViewModel.refreshData()
        } else {
            selectedPost = savedInstanceState.get(PARAM_SELECTED_POST) as PostAndUser?
            selectedPost?.let { displayPost(it) } ?: clearDetailFragment()
        }
        commonViewModel.eventMessage.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        selectedPost?.let { outState.putSerializable(PARAM_SELECTED_POST, it) }
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        selectedPost = null
        if (!hasSecondPane && supportFragmentManager.findFragmentByTag(TAG_DETAIL_FRAGMENT) != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.master_fragment,
                    PostListFragment.newInstance(),
                    TAG_MASTER_FRAGMENT
                )
                .commit()
        } else {
            super.onBackPressed()
        }
    }

    override fun onPostClicked(postAndUser: PostAndUser) {
        selectedPost = postAndUser
        displayPost(postAndUser)
    }

    private fun displayList() {
        val listFragment = supportFragmentManager.findFragmentByTag(TAG_MASTER_FRAGMENT)
            ?: PostListFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.master_fragment, listFragment)
            .commitNow()
    }

    private fun displayPost(postAndUser: PostAndUser) {
        val containerViewId = if (hasSecondPane) {
            R.id.detail_fragment
        } else {
            R.id.master_fragment
        }
        supportFragmentManager.beginTransaction()
            .replace(
                containerViewId,
                PostDetailFragment.newInstance(postAndUser),
                TAG_DETAIL_FRAGMENT
            )
            .commitNow()

    }

    private fun clearDetailFragment() {
        supportFragmentManager.findFragmentByTag(TAG_DETAIL_FRAGMENT)?.let {
            supportFragmentManager
                .beginTransaction()
                .remove(it)
                .commit()
        }


    }

}