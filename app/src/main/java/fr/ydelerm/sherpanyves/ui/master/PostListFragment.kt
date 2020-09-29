package fr.ydelerm.sherpanyves.ui.master

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.ydelerm.sherpanyves.R
import fr.ydelerm.sherpanyves.databinding.PostListFragmentBinding
import fr.ydelerm.sherpanyves.model.PostAndUser
import fr.ydelerm.sherpanyves.viewmodel.CommonViewModel
import kotlinx.android.synthetic.main.post_list_fragment.*

/**
 * A [Fragment] that displays the list of the posts
 */
class PostListFragment : Fragment() {

    companion object {
        fun newInstance() = PostListFragment()
    }

    lateinit var commonViewModel: CommonViewModel
    private lateinit var postAdapter: PostAdapter
    lateinit var observer: (t: PagedList<PostAndUser>) -> Unit
    private lateinit var binding: PostListFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.post_list_fragment,
            container,
            false
        )
        binding.clickListener = View.OnClickListener { refresh(commonViewModel) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        commonViewModel = ViewModelProvider(activity!!).get(CommonViewModel::class.java)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        recyclerView.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        recyclerView.isSaveEnabled = true
        postAdapter = PostAdapter(activity!!, activity as PostClickedListener)
        recyclerView.adapter = postAdapter

        subscribe()

        swipeContainer.setOnRefreshListener { refresh(commonViewModel) }
        commonViewModel.isRefreshing.observe(viewLifecycleOwner) {
            swipeContainer.isRefreshing = it
            refreshEmptyView()
        }
    }

    private fun subscribe() {
        observer = {
            refreshEmptyView()
            postAdapter.submitList(it)
        }
        commonViewModel.postsAndUsers.observe(viewLifecycleOwner, observer)
    }

    private fun refreshEmptyView() {
        binding.emptyViewVisible = (commonViewModel.postsAndUsers.value
            ?: ArrayList()).isEmpty() && commonViewModel.isFiteringEnabled.value == false
    }

    private fun refresh(commonViewModel: CommonViewModel) {
        binding.emptyViewVisible = false
        commonViewModel.refreshData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.post_list_fragment_menu, menu)
        val actionView = menu.findItem(R.id.menu_action_search)?.actionView as SearchView

        actionView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                commonViewModel.postsAndUsers.removeObserver(observer)
                commonViewModel.filterPosts(query)
                subscribe()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                commonViewModel.postsAndUsers.removeObserver(observer)
                commonViewModel.filterPosts(newText)
                subscribe()
                return true
            }
        })
    }

}