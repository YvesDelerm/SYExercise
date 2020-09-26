package fr.ydelerm.sherpanyves.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.squareup.picasso.Picasso


@Entity
data class Photo(
    @ColumnInfo(name = "photoAlbumId")
    val albumId: Int,
    @PrimaryKey @ColumnInfo(name = "photoId")
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) {
    companion object {

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String) {
            Picasso.get()
                .load(url)
                .placeholder(CircularProgressDrawable(view.context))
                .error(android.R.drawable.ic_menu_camera)
                .into(view)

        }
    }
}