package fr.ydelerm.sherpanyves.ui.master

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.ydelerm.sherpanyves.R
import fr.ydelerm.sherpanyves.databinding.PostListFragmentBinding
import fr.ydelerm.sherpanyves.viewmodel.CommonViewModel
import kotlinx.android.synthetic.main.post_list_fragment.*

class PostListFragment : Fragment() {

    companion object {
        fun newInstance() = PostListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<PostListFragmentBinding>(
            layoutInflater,
            R.layout.post_list_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val allViewModel = ViewModelProvider(activity!!).get(CommonViewModel::class.java)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        recyclerView.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        recyclerView.isSaveEnabled = true
        val postAdapter = PostAdapter(activity!!, activity as PostClickedListener)
        recyclerView.adapter = postAdapter

        allViewModel.allPostsAndUsers.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                buttonRefresh.visibility = View.VISIBLE
                textviewError.visibility = View.VISIBLE
            } else {
                postAdapter.submitList(it)
                buttonRefresh.visibility = View.GONE
                textviewError.visibility = View.GONE
            }
            swipeContainer.isRefreshing = false
        }

        buttonRefresh.setOnClickListener { refresh(allViewModel) }
        swipeContainer.setOnRefreshListener { refresh(allViewModel) }
    }

    private fun refresh(commonViewModel: CommonViewModel) {
        buttonRefresh.visibility = View.GONE
        textviewError.visibility = View.GONE
        swipeContainer.isRefreshing = true
        commonViewModel.refreshData()
    }

}