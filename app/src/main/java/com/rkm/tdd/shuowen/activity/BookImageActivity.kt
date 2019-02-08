package com.rkm.tdd.shuowen.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.adapter.BookImagePageAdapter
import com.rkm.tdd.shuowen.util.ext.observe
import com.rkm.tdd.shuowen.viewmodel.BookImageListViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.book_image_activity.*
import javax.inject.Inject

class BookImageActivity : AppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        const val EXTRA_VOLUME_ID = "volume_id"
        const val EXTRA_IMAGE_ID = "image_id"
    }

    @Inject lateinit var factory: ViewModelProvider.Factory
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private val adapter = BookImagePageAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_image_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        view_pager.adapter = adapter

        val viewModel = ViewModelProviders.of(this, factory).get(BookImageListViewModel::class.java)
        viewModel.imageIds.observe(this) {
            it ?: return@observe

            val imageIds = it.reversed()

            adapter.imageIds = imageIds
            adapter.notifyDataSetChanged()

            val imageId = intent.getIntExtra(EXTRA_IMAGE_ID, 1)
            view_pager.setCurrentItem(imageIds.indexOf(imageId), false)
        }

        viewModel.volume.observe(this) {
            it ?: return@observe

            title = "第${it.volume}卷"
        }

        viewModel.volumeId.value = intent.getIntExtra(EXTRA_VOLUME_ID, 1)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}
