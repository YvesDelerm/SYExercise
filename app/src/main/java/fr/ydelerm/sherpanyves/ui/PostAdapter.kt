package fr.ydelerm.sherpanyves.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import fr.ydelerm.sherpanyves.R
import fr.ydelerm.sherpanyves.model.PostAndUser
import fr.ydelerm.sherpanyves.viewmodel.AllViewModel

class PostAdapter(private val postsAndUsers: List<PostAndUser>, viewModelStoreOwner: ViewModelStoreOwner, private val postClickedListener: PostClickedListener) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    val allViewModel = ViewModelProvider(viewModelStoreOwner).get(AllViewModel::class.java)

    class PostViewHolder(@NonNull v: View) : RecyclerView.ViewHolder(v) {
        internal var postLayout: ConstraintLayout = v.findViewById(R.id.post_layout)
        internal var textViewTitle: TextView = v.findViewById(R.id.textViewTitle)
        internal var textViewEmail: TextView = v.findViewById(R.id.textViewEmail)
        internal var imageButtonDelete: ImageButton = v.findViewById(R.id.imageButtonDelete)
    }


    @NonNull
    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_post_list_item, parent, false)
        return PostViewHolder(view)

    }

    override fun onBindViewHolder(@NonNull holder: PostViewHolder, position: Int) {
        val post = postsAndUsers[position]

        holder.textViewTitle.text = post.post.title
        holder.textViewEmail.text = post.user.email
        holder.postLayout.setOnClickListener{ _ -> onPostSelected(holder)}
        holder.imageButtonDelete.setOnClickListener { deletePost(holder) }
    }

    private fun deletePost(@NonNull holder: PostViewHolder) {
        val postAndUser = postsAndUsers[holder.adapterPosition]
        allViewModel.deletePost(postAndUser.post)
    }

    private fun onPostSelected(@NonNull holder:PostViewHolder) {
        val postAndUser = postsAndUsers[holder.adapterPosition]
        postClickedListener.onPostClicked(postAndUser)
    }


    override fun getItemCount(): Int {
        return postsAndUsers.size
    }
}