package com.rkm.tdd.shuowen.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.adapter.WordItemAdapter
import com.rkm.tdd.shuowen.di.Injectable
import com.rkm.tdd.shuowen.util.ext.observe
import com.rkm.tdd.shuowen.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable {

    private val adapter = WordItemAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        word_list.adapter = adapter

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.wordItems.observe(this) {
            if (it == null) return@observe

            adapter.items = it
        }
        viewModel.search.value = ""
    }
}
