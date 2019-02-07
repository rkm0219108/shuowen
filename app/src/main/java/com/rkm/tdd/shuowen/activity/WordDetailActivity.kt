package com.rkm.tdd.shuowen.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.databinding.NoteItemBinding
import com.rkm.tdd.shuowen.databinding.OldWordNoteItemBinding
import com.rkm.tdd.shuowen.databinding.WordDetailActivityBinding
import com.rkm.tdd.shuowen.di.Injectable
import com.rkm.tdd.shuowen.util.ext.observe
import com.rkm.tdd.shuowen.viewmodel.WordDetailViewModel
import kotlinx.android.synthetic.main.word_detail_activity.*
import javax.inject.Inject

class WordDetailActivity : AppCompatActivity(), Injectable {

    companion object {
        const val EXTRA_WORD_ID = "word_id"
    }

    @Inject lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<WordDetailActivityBinding>(this, R.layout.word_detail_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProviders.of(this, factory).get(WordDetailViewModel::class.java)

        viewModel.word.observe(this) {
            it ?: return@observe

            title = it.word
            binding.word = it
        }
        viewModel.notes.observe(this) { notes ->
            notes ?: return@observe

            notes.forEach {
                binding.llNotes.addView(
                    DataBindingUtil.inflate<NoteItemBinding>(
                        layoutInflater,
                        R.layout.note_item,
                        null,
                        false
                    ).apply {
                        note = it
                    }.root
                )
            }
        }
        viewModel.oldWordNotes.observe(this) { notes ->
            notes ?: return@observe

            notes.forEach {
                binding.llOldWordNotes.addView(
                    DataBindingUtil.inflate<OldWordNoteItemBinding>(
                        layoutInflater,
                        R.layout.old_word_note_item,
                        null,
                        false
                    ).apply {
                        note = it
                    }.root
                )
            }
        }
        viewModel.wordId.value = intent.getIntExtra(EXTRA_WORD_ID, 1)
    }
}
