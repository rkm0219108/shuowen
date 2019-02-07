package com.rkm.tdd.shuowen.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.databinding.EditNoteItemBinding
import com.rkm.tdd.shuowen.databinding.EditOldWordNoteItemBinding
import com.rkm.tdd.shuowen.databinding.WordEditActivityBinding
import com.rkm.tdd.shuowen.di.Injectable
import com.rkm.tdd.shuowen.util.ext.observe
import com.rkm.tdd.shuowen.viewmodel.WordEditViewModel
import kotlinx.android.synthetic.main.word_edit_activity.*
import javax.inject.Inject

class WordEditActivity : AppCompatActivity(), Injectable {

    companion object {
        const val EXTRA_WORD_ID = "word_id"
    }

    @Inject lateinit var factory: ViewModelProvider.Factory

    private lateinit var binding: WordEditActivityBinding
    private lateinit var viewModel: WordEditViewModel

    private val noteItemBindings = mutableListOf<EditNoteItemBinding>()
    private val oldWordNoteItemBindings = mutableListOf<EditOldWordNoteItemBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.word_edit_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this, factory).get(WordEditViewModel::class.java)
        viewModel.word.observe(this) {
            it ?: return@observe

            title = it.word
            binding.word = it
        }
        viewModel.notes.observe(this) { notes ->
            notes ?: return@observe

            notes.forEach {
                binding.llNotes.addView(
                    DataBindingUtil.inflate<EditNoteItemBinding>(
                        layoutInflater,
                        R.layout.edit_note_item,
                        null,
                        false
                    ).apply {
                        note = it
                        noteItemBindings.add(this)
                    }.root
                )
            }
        }
        viewModel.oldWordNotes.observe(this) { notes ->
            notes ?: return@observe

            notes.forEach {
                binding.llOldWordNotes.addView(
                    DataBindingUtil.inflate<EditOldWordNoteItemBinding>(
                        layoutInflater,
                        R.layout.edit_old_word_note_item,
                        null,
                        false
                    ).apply {
                        note = it
                        oldWordNoteItemBindings.add(this)
                    }.root
                )
            }
        }

        viewModel.wordId.value = intent.getIntExtra(EXTRA_WORD_ID, 1)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_word_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_done -> {
                save()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun save() {
        val word = viewModel.word.value
        word ?: return

        word.word = binding.editWord.text.toString()
        word.content = binding.editContent.text.toString()
        word.oldWord = binding.editOldWord.text.toString()
        viewModel.save(word)

        noteItemBindings.forEach {
            val note = it.note
            note ?: return

            note.content = it.editContent.text.toString()
            note.note = it.editNote.text.toString()
            viewModel.save(note)
        }

        oldWordNoteItemBindings.forEach {
            val note = it.note
            note ?: return

            note.content = it.editContent.text.toString()
            note.note = it.editNote.text.toString()
            viewModel.save(note)
        }
    }
}
