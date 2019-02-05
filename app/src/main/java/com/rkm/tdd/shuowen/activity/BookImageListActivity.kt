package com.rkm.tdd.shuowen.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.adapter.BookImageAdapter
import com.rkm.tdd.shuowen.db.model.BookImage
import com.rkm.tdd.shuowen.di.Injectable
import com.rkm.tdd.shuowen.util.ext.observe
import com.rkm.tdd.shuowen.viewmodel.ImageListViewModel
import kotlinx.android.synthetic.main.book_image_list_activity.*
import timber.log.Timber
import javax.inject.Inject

class BookImageListActivity : AppCompatActivity(), Injectable {

    companion object {
        const val EXTRA_VOLUME = "volume"
    }

    @Inject lateinit var factory: ViewModelProvider.Factory

    private val adapter = BookImageAdapter(object : BookImageAdapter.Callback {
        override fun onImageClick(image: BookImage) {
            Timber.i(image.toString())
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_image_list_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        img_list.adapter = adapter

        val viewModel = ViewModelProviders.of(this, factory).get(ImageListViewModel::class.java)
        viewModel.images.observe(this) {
            it ?: return@observe

            adapter.images = it
        }

        val volume = intent.getIntExtra(EXTRA_VOLUME, 1)
        title = "第${volume}卷"
        viewModel.volume.value = volume
    }
}
