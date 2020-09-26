package fr.ydelerm.sherpanyves.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import fr.ydelerm.sherpanyves.R
import fr.ydelerm.sherpanyves.databinding.PostDetailFragmentBinding
import fr.ydelerm.sherpanyves.model.PostAndUser
import fr.ydelerm.sherpanyves.viewmodel.AllViewModel
import kotlinx.android.synthetic.main.post_detail_fragment.*

private const val ARG_POST_AND_USER = "postAndUser"

/**
 * A [Fragment] that displays the detail of a post
 * Use the [PostDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostDetailFragment : Fragment() {
    private var postAndUser: PostAndUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            postAndUser = it.getSerializable(ARG_POST_AND_USER) as PostAndUser
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val listItemBinding = DataBindingUtil.inflate<PostDetailFragmentBinding>(layoutInflater, R.layout.post_detail_fragment, container, false)
        listItemBinding.postAndUser = postAndUser
        return listItemBinding.root
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
                    putSerializable(ARG_POST_AND_USER, postAndUser)
                }
            }
    }
}