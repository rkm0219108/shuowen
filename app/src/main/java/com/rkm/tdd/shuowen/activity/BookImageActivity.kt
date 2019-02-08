package com.rkm.tdd.shuowen.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.di.Injectable
import com.rkm.tdd.shuowen.util.SquareCenterCrop
import com.rkm.tdd.shuowen.util.ext.observe
import com.rkm.tdd.shuowen.viewmodel.BookImageViewModel
import kotlinx.android.synthetic.main.book_image_activity.*
import javax.inject.Inject

class BookImageActivity : AppCompatActivity(), Injectable {

    companion object {
        const val EXTRA_IMAGE_URL = "image_url"
    }

    @Inject lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_image_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProviders.of(this, factory).get(BookImageViewModel::class.java)
        viewModel.scaleLevel.observe(this) {
            it ?: return@observe

            image.mediumScale = it
        }

        val imgUrl = intent.getStringExtra(EXTRA_IMAGE_URL)
        Glide.with(this).load(imgUrl)
            .apply(placeholderOf(R.drawable.progress_animation).transform(SquareCenterCrop().apply {
                resizeListener = object : SquareCenterCrop.ResizeListener {
                    override fun onResized(medium: Float) {
                        viewModel.update(medium)
                    }
                }
            })).into(image)

        viewModel.imageUrl.value = imgUrl
    }
}
