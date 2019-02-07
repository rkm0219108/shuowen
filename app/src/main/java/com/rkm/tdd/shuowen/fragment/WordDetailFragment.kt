package com.rkm.tdd.shuowen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.databinding.NoteItemBinding
import com.rkm.tdd.shuowen.databinding.OldWordNoteItemBinding
import com.rkm.tdd.shuowen.databinding.WordDetailFragmentBinding
import com.rkm.tdd.shuowen.di.Injectable
import com.rkm.tdd.shuowen.util.AutoClearedValue
import com.rkm.tdd.shuowen.util.ext.observe
import com.rkm.tdd.shuowen.viewmodel.WordDetailViewModel
import javax.inject.Inject

class WordDetailFragment : Fragment(), Injectable {

    companion object {
        const val EXTRA_WORD_ID = "word_id"
    }

    @Inject lateinit var factory: ViewModelProvider.Factory

    private lateinit var binding: AutoClearedValue<WordDetailFragmentBinding>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this, factory).get(WordDetailViewModel::class.java)
        viewModel.word.observe(this) {
            it ?: return@observe

            binding.get()?.word = it
        }
        viewModel.notes.observe(this) { notes ->
            notes ?: return@observe

            notes.forEach {
                binding.get()?.llNotes?.addView(
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
                binding.get()?.llOldWordNotes?.addView(
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

        viewModel.wordId.value = arguments?.getInt(EXTRA_WORD_ID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        DataBindingUtil.inflate<WordDetailFragmentBinding>(
            inflater,
            R.layout.word_detail_fragment,
            container,
            false
        ).apply {
            binding = AutoClearedValue(this@WordDetailFragment, this)
        }.root
}
