package com.rkm.tdd.shuowen.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.adapter.WordItemAdapter
import com.rkm.tdd.shuowen.di.Injectable
import com.rkm.tdd.shuowen.model.WordItem
import com.rkm.tdd.shuowen.util.ext.observe
import com.rkm.tdd.shuowen.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_activity.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable {

    private val adapter = WordItemAdapter(object : WordItemAdapter.Callback {
        override fun onItemClick(item: WordItem) {
            Timber.i(item.toString())
        }
    })

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        initNav()

        word_list.adapter = adapter

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.wordItems.observe(this) {
            if (it == null) return@observe

            adapter.items = it
        }
        viewModel.search.value = ""
    }

    private fun initNav() {
        toolbar.setNavigationIcon(R.drawable.ic_nav_menu)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, BookVolumeListActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu ?: return false

        menuInflater.inflate(R.menu.menu_main, menu)
        val item = menu.findItem(R.id.menu_search)
        val searchView: SearchView = item.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.search.value = newText
                return false
            }
        })
        return true
    }
}
