package fr.ydelerm.sherpanyves.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import fr.ydelerm.sherpanyves.R
import fr.ydelerm.sherpanyves.databinding.PostListItemBinding
import fr.ydelerm.sherpanyves.model.PostAndUser
import fr.ydelerm.sherpanyves.viewmodel.AllViewModel

class PostAdapter(
    private val postsAndUsers: List<PostAndUser>,
    viewModelStoreOwner: ViewModelStoreOwner,
    private val postClickedListener: PostClickedListener
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val allViewModel = ViewModelProvider(viewModelStoreOwner).get(AllViewModel::class.java)

    class PostViewHolder(@NonNull val binding: PostListItemBinding,
                         postClickedListener: PostClickedListener,
                         allViewModel: AllViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.postAndUser?.let {
                    postClickedListener.onPostClicked(it)
                }
            }

            binding.setDeleteClickListener {
                binding.postAndUser?.let {
                    allViewModel.deletePost(it.post)
                }
            }
        }

        fun bind(item: PostAndUser) {
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
        val binding = DataBindingUtil.inflate<PostListItemBinding>(LayoutInflater.from(parent.context), R.layout.post_list_item, parent, false)
        return PostViewHolder(binding, postClickedListener, allViewModel)
    }

    override fun onBindViewHolder(@NonNull holder: PostViewHolder, position: Int) {
        val postAndUser = postsAndUsers[position]
        holder.bind(postAndUser)
    }

    override fun getItemCount(): Int {
        return postsAndUsers.size
    }
}