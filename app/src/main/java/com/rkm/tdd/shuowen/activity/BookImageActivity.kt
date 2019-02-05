package com.rkm.tdd.shuowen.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rkm.tdd.shuowen.R
import kotlinx.android.synthetic.main.book_image_activity.*

class BookImageActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_IMAGE_URL = "image_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_image_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imgUrl = intent.getStringExtra(EXTRA_IMAGE_URL)
        Glide.with(this).load(imgUrl).apply(RequestOptions.placeholderOf(R.drawable.progress_animation)).into(image)
    }
}
