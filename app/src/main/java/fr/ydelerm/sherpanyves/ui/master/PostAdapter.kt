package fr.ydelerm.sherpanyves.ui.master

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import fr.ydelerm.sherpanyves.R
import fr.ydelerm.sherpanyves.databinding.PostListItemBinding
import fr.ydelerm.sherpanyves.model.PostAndUser
import fr.ydelerm.sherpanyves.viewmodel.CommonViewModel

/**
 * Post adapter: a [PagedListAdapter] that binds [PostAndUser] into [RecyclerView] cells
 */
class PostAdapter(
    viewModelStoreOwner: ViewModelStoreOwner,
    private val postClickedListener: PostClickedListener
) : PagedListAdapter<PostAndUser, PostAdapter.PostViewHolder>(PostDiffUtil()) {

    private val allViewModel =
        ViewModelProvider(viewModelStoreOwner).get(CommonViewModel::class.java)

    class PostViewHolder(
        @NonNull val binding: PostListItemBinding,
        postClickedListener: PostClickedListener,
        commonViewModel: CommonViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.postAndUser?.let {
                    postClickedListener.onPostClicked(it)
                }
            }

            binding.setDeleteClickListener {
                binding.postAndUser?.let {
                    commonViewModel.deletePost(it.post)
                }
            }
        }

        fun bind(item: PostAndUser?) {
            binding.apply {
                postAndUser = item
                executePendingBindings()
            }
        }
    }


    @NonNull
    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val binding = DataBindingUtil.inflate<PostListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.post_list_item,
            parent,
            false
        )
        return PostViewHolder(binding, postClickedListener, allViewModel)
    }

    override fun onBindViewHolder(@NonNull holder: PostViewHolder, position: Int) {
        val postAndUser = getItem(position)
        holder.bind(postAndUser)
    }

    internal class PostDiffUtil : DiffUtil.ItemCallback<PostAndUser>() {
        override fun areItemsTheSame(oldItem: PostAndUser, newItem: PostAndUser) =
            oldItem.post.id == newItem.post.id

        override fun areContentsTheSame(oldItem: PostAndUser, newItem: PostAndUser) =
            oldItem == newItem
    }
}