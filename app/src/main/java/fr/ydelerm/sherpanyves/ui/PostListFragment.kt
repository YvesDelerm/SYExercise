package fr.ydelerm.sherpanyves.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.ydelerm.sherpanyves.R
import fr.ydelerm.sherpanyves.viewmodel.AllViewModel
import fr.ydelerm.sherpanyves.viewmodel.PostListViewModel
import kotlinx.android.synthetic.main.post_list_fragment.*

class PostListFragment : Fragment() {

    companion object {
        fun newInstance() = PostListFragment()
    }

    private lateinit var postListViewModel: PostListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.post_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        postListViewModel = ViewModelProvider(this).get(PostListViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val allViewModel = ViewModelProvider(this).get(AllViewModel::class.java)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.activity)

        allViewModel.allPostsAndUsers.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                buttonRefresh.visibility = View.VISIBLE
                textviewError.visibility = View.VISIBLE
            } else {
                recyclerView.swapAdapter(PostAdapter(it, this /*, this*/), true)
                buttonRefresh.visibility = View.GONE
                textviewError.visibility = View.GONE
            }
            swipeContainer.isRefreshing = false
        }

        allViewModel.allAlbumWithPhotos.observe(viewLifecycleOwner) {
            Log.e("TEST", "albums " + it.size)
        }

        allViewModel.userWithAlbumsAndPhotos.observe(viewLifecycleOwner) {
            Log.e("TEST", "user albums " + it?.albumsWithPhotos?.size)
        }

        buttonRefresh.setOnClickListener { refresh(allViewModel) }
        swipeContainer.setOnRefreshListener { refresh(allViewModel) }

        refresh(allViewModel)
    }

    private fun refresh(allViewModel: AllViewModel) {
        buttonRefresh.visibility = View.GONE
        textviewError.visibility = View.GONE
        swipeContainer.isRefreshing = true
        allViewModel.refreshAll()
    }

}