package com.rkm.tdd.shuowen.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.adapter.VolumeItemAdapter
import com.rkm.tdd.shuowen.di.Injectable
import com.rkm.tdd.shuowen.model.VolumeItem
import com.rkm.tdd.shuowen.util.ext.observe
import com.rkm.tdd.shuowen.viewmodel.VolumeListViewModel
import kotlinx.android.synthetic.main.book_volume_list_activity.*
import javax.inject.Inject

class BookVolumeListActivity : AppCompatActivity(), Injectable {

    @Inject lateinit var factory: ViewModelProvider.Factory

    private val adapter = VolumeItemAdapter(object : VolumeItemAdapter.Callback {
        override fun onItemClick(item: VolumeItem) {
            startActivity(Intent(this@BookVolumeListActivity, BookImagesActivity::class.java).apply {
                putExtra(BookImagesActivity.EXTRA_VOLUME_ID, item.volumeId)
            })
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_volume_list_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        volume_list.adapter = adapter

        val viewModel = ViewModelProviders.of(this, factory).get(VolumeListViewModel::class.java)
        viewModel.volumes.observe(this) {
            it ?: return@observe

            adapter.items = it
        }
    }
}
