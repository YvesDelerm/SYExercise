package fr.ydelerm.sherpanyves.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fr.ydelerm.sherpanyves.R
import fr.ydelerm.sherpanyves.databinding.AlbumListItemBinding
import fr.ydelerm.sherpanyves.databinding.PhotoListItemBinding
import fr.ydelerm.sherpanyves.model.AlbumWithPhotos
import fr.ydelerm.sherpanyves.model.Photo

const val ITEM_TYPE_ALBUM_HEADER = 0
const val ITEM_TYPE_PHOTO = 1

class AlbumAdapter(albumsWithPhotos: List<AlbumWithPhotos>) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>(), HeaderClickListener {
    private val itemsToDisplay: MutableList<AlbumViewHolder.Item>
    private val photosForAlbums: MutableMap<String, List<AlbumViewHolder.Item>>

    init {
        photosForAlbums = HashMap()
        itemsToDisplay = ArrayList()
        for (album in albumsWithPhotos) {
            photosForAlbums[album.album.title] = album.photos.map { PhotoItem(it) }
            itemsToDisplay.add(AlbumItem(album, true))
        }
    }

    abstract class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal abstract fun bind(item: Item)

        abstract class Item {
            abstract val viewType: Int
        }
    }

    class AlbumItem(val album: AlbumWithPhotos, var isCollapsed: Boolean) : AlbumViewHolder.Item() {
        override val viewType: Int
            get() = ITEM_TYPE_ALBUM_HEADER
    }


    class AlbumHeaderViewHolder(
        private val binding: AlbumListItemBinding,
        private val headerClickListener: HeaderClickListener
    ) : AlbumViewHolder(binding.root) {

        override fun bind(item: Item) {
            binding.apply {
                albumWithPhotos = (item as AlbumItem).album
                clickListener = View.OnClickListener {
                    headerClickListener.onHeaderClick(
                        item,
                        adapterPosition
                    )
                }
                isCollapsed = item.isCollapsed
                executePendingBindings()
            }
        }

    }

    class PhotoItem(val photo: Photo) : AlbumViewHolder.Item() {
        override val viewType: Int
            get() = ITEM_TYPE_PHOTO
    }

    class PhotoViewHolder(private val binding: PhotoListItemBinding) :
        AlbumViewHolder(binding.root) {

        override fun bind(item: Item) {
            binding.apply {
                photo = (item as PhotoItem).photo
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return when (viewType) {
            ITEM_TYPE_ALBUM_HEADER -> {
                val binding = DataBindingUtil.inflate<AlbumListItemBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.album_list_item,
                    parent,
                    false
                )
                AlbumHeaderViewHolder(binding, this)
            }
            else -> {
                val binding = DataBindingUtil.inflate<PhotoListItemBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.photo_list_item,
                    parent,
                    false
                )
                PhotoViewHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(itemsToDisplay[position])
    }

    override fun getItemCount(): Int {
        return itemsToDisplay.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemsToDisplay[position] is AlbumItem) {
            ITEM_TYPE_ALBUM_HEADER
        } else {
            ITEM_TYPE_PHOTO
        }
    }

    override fun onHeaderClick(albumItem: AlbumItem, position: Int) {
        if (albumItem.isCollapsed) {
            expand(albumItem, position)
        } else {
            collapse(albumItem, position)
        }
        albumItem.isCollapsed = !albumItem.isCollapsed
    }

    private fun expand(albumItem: AlbumItem, position: Int) {
        val photosToAdd: Collection<AlbumViewHolder.Item> =
            photosForAlbums[albumItem.album.album.title] ?: ArrayList()
        itemsToDisplay.addAll(position + 1, photosToAdd)
        notifyItemChanged(position)
        notifyItemRangeInserted(position + 1, photosToAdd.size)
    }

    private fun collapse(albumItem: AlbumItem, position: Int) {
        val photosToRemove: Collection<AlbumViewHolder.Item> =
            photosForAlbums[albumItem.album.album.title] ?: ArrayList()
        val filteredList =
            itemsToDisplay.filterIndexed { index, _ -> index !in (position + 1)..(position + photosToRemove.size) }
        itemsToDisplay.clear()
        itemsToDisplay.addAll(filteredList)
        notifyItemChanged(position)
        notifyItemRangeRemoved(position + 1, photosToRemove.size)
    }
}