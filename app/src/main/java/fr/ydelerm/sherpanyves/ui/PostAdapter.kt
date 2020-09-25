package fr.ydelerm.sherpanyves.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import fr.ydelerm.sherpanyves.R
import fr.ydelerm.sherpanyves.model.PostAndUser

class PostAdapter(private val posts: List<PostAndUser>/*, private val clickListener: PostClickedListener*/) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(@NonNull v: View) : RecyclerView.ViewHolder(v) {
        internal var postLayout: ConstraintLayout = v.findViewById(R.id.cars_layout)
        internal var textViewTitle: TextView = v.findViewById(R.id.textViewTitle)
        internal var textViewEmail: TextView = v.findViewById(R.id.textViewEmail)
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
        val post = posts[position]

        holder.textViewTitle.text = post.post.title
        holder.textViewEmail.text = post.user.email
        holder.postLayout.setOnClickListener{ _ -> onPostSelected(holder)}
    }

    private fun onPostSelected(@NonNull holder:PostViewHolder) {
        val post = posts[holder.adapterPosition]
        //clickListener.onPostClicked(holder.adapterPosition, post)
    }


    override fun getItemCount(): Int {
        return posts.size
    }
}