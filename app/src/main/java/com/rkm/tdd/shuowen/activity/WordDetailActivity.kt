package com.rkm.tdd.shuowen.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.adapter.WordDetailPageAdapter
import com.rkm.tdd.shuowen.databinding.WordDetailActivityBinding
import com.rkm.tdd.shuowen.util.ext.observe
import com.rkm.tdd.shuowen.viewmodel.WordDetailListViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.word_detail_activity.*
import javax.inject.Inject

class WordDetailActivity : AppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        const val EXTRA_WORD_ID = "word_id"
        const val EXTRA_SEARCH_WORD = "search_word"
    }

    @Inject lateinit var factory: ViewModelProvider.Factory
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private val adapter = WordDetailPageAdapter(supportFragmentManager)

    private lateinit var viewModel: WordDetailListViewModel

    private val listener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            val wordIds = viewModel.wordIds.value
            wordIds ?: return

            viewModel.wordId.value = wordIds[position]
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<WordDetailActivityBinding>(this, R.layout.word_detail_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.viewPager.adapter = adapter
        binding.viewPager.addOnPageChangeListener(listener)

        viewModel = ViewModelProviders.of(this, factory).get(WordDetailListViewModel::class.java)
        viewModel.wordIds.observe(this) {
            it ?: return@observe

            adapter.wordIds = it
            adapter.notifyDataSetChanged()

            val wordId = intent.getIntExtra(EXTRA_WORD_ID, 1)
            binding.viewPager.currentItem = it.indexOf(wordId)
            viewModel.wordId.value = wordId
        }
        viewModel.word.observe(this) {
            it ?: return@observe

            title = it.word
        }

        viewModel.search.value = intent.getStringExtra(EXTRA_SEARCH_WORD)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}
