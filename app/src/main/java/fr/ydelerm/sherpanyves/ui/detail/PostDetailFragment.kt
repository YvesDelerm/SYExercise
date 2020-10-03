package fr.ydelerm.sherpanyves.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.ydelerm.sherpanyves.R
import fr.ydelerm.sherpanyves.databinding.PostDetailFragmentBinding
import fr.ydelerm.sherpanyves.model.PostAndUser
import fr.ydelerm.sherpanyves.ui.NavigationChildFragment
import fr.ydelerm.sherpanyves.viewmodel.CommonViewModel
import kotlinx.android.synthetic.main.post_detail_fragment.*

private const val ARG_POST_AND_USER = "postAndUser"

/**
 * A [Fragment] that displays the detail of a post
 * Use the [PostDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostDetailFragment : NavigationChildFragment() {
    private var postAndUser: PostAndUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            postAndUser = it.getParcelable(ARG_POST_AND_USER) as? PostAndUser
        }
    }

    override fun onCreateChildView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val listItemBinding = DataBindingUtil.inflate<PostDetailFragmentBinding>(
            layoutInflater,
            R.layout.post_detail_fragment,
            container,
            false
        )
        listItemBinding.postAndUser = postAndUser
        return listItemBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val allViewModel = ViewModelProvider(activity!!).get(CommonViewModel::class.java)

        recyclerViewAlbums.layoutManager = LinearLayoutManager(this.activity)
        recyclerViewAlbums.isSaveEnabled = true

        postAndUser?.let { _postAndUser ->
            allViewModel.getUserWithAlbumsAndPhotos(_postAndUser.user.id)
                .observe(viewLifecycleOwner) {
                    it?.let {
                        val albumAdapter = AlbumAdapter(it.albumsWithPhotos)
                        recyclerViewAlbums.swapAdapter(albumAdapter, true)
                        // renewing itemdecoration every time, so it has the latest adapter
                        if (recyclerViewAlbums.itemDecorationCount > 0) {
                            recyclerViewAlbums.removeItemDecorationAt(0)
                        }
                        recyclerViewAlbums.addItemDecoration(
                            HeaderItemDecoration(
                                recyclerViewAlbums,
                                albumAdapter
                            ) { itemPosition ->
                                if (itemPosition >= 0 && itemPosition < albumAdapter.itemCount) {
                                    albumAdapter.getItemViewType(itemPosition) == ITEM_TYPE_ALBUM_HEADER
                                } else false
                            })
                    }
                }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param postAndUser the post to display with its user
         * @return A new instance of fragment PostDetailFragment.
         */
        @JvmStatic
        fun newInstance(postAndUser: PostAndUser) =
            PostDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_POST_AND_USER, postAndUser)
                }
            }
    }
}