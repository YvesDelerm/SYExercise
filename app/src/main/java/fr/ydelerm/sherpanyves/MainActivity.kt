package fr.ydelerm.sherpanyves

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import fr.ydelerm.sherpanyves.viewmodel.AllViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: AllViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.screens_title)

        viewModel = ViewModelProvider(this).get(AllViewModel::class.java)

        viewModel.refreshAll()

        viewModel.allAlbums.observe(this) { refreshText() }
        viewModel.allPhotos.observe(this) { refreshText() }
        viewModel.allPosts.observe(this) { refreshText() }
        viewModel.allUsers.observe(this) { refreshText() }
    }

    private fun refreshText() {
        textView.text = "${viewModel.allUsers.value?.size} ${viewModel.allPosts.value?.size} ${viewModel.allAlbums.value?.size} ${viewModel.allPhotos.value?.size}"
    }
}