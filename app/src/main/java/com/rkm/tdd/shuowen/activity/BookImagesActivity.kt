package com.rkm.tdd.shuowen.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.adapter.BookImageAdapter
import com.rkm.tdd.shuowen.db.model.BookImage
import com.rkm.tdd.shuowen.di.Injectable
import com.rkm.tdd.shuowen.util.ext.observe
import com.rkm.tdd.shuowen.viewmodel.ImageListViewModel
import kotlinx.android.synthetic.main.book_images_activity.*
import javax.inject.Inject

class BookImagesActivity : AppCompatActivity(), Injectable {

    companion object {
        const val EXTRA_VOLUME_ID = "volume_id"
    }

    @Inject lateinit var factory: ViewModelProvider.Factory

    private val adapter = BookImageAdapter(object : BookImageAdapter.Callback {
        override fun onImageClick(image: BookImage) {
            startActivity(Intent(this@BookImagesActivity, BookImageActivity::class.java).apply {
                putExtra(BookImageActivity.EXTRA_IMAGE_URL, image.imgUrl)
            })
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_images_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val manager = GridLayoutManager(this, 3)
        images.layoutManager = manager
        images.adapter = adapter

        val viewModel = ViewModelProviders.of(this, factory).get(ImageListViewModel::class.java)
        viewModel.images.observe(this) {
            it ?: return@observe

            adapter.images = it
        }
        viewModel.volume.observe(this) {
            it ?: return@observe

            title = "第${it.volume}卷"
        }

        viewModel.volumeId.value = intent.getIntExtra(EXTRA_VOLUME_ID, 1)
    }
}
