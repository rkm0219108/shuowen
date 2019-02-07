package com.rkm.tdd.shuowen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rkm.tdd.shuowen.db.model.Note
import com.rkm.tdd.shuowen.db.model.OldWordNote
import com.rkm.tdd.shuowen.db.model.Word
import com.rkm.tdd.shuowen.repository.WordRepository
import com.rkm.tdd.shuowen.util.AbsentLiveData
import com.rkm.tdd.shuowen.util.ext.switchMap
import javax.inject.Inject

class WordDetailViewModel @Inject constructor(repository: WordRepository) : ViewModel() {

    val wordId = MutableLiveData<Int>()
    val word: LiveData<Word>
    val notes: LiveData<List<Note>>
    val oldWordNotes: LiveData<List<OldWordNote>>

    init {
        word = wordId.switchMap {
            if (it == null) AbsentLiveData.create()
            else repository.word(it)
        }

        notes = wordId.switchMap {
            if (it == null) AbsentLiveData.create()
            else repository.notes(it)
        }

        oldWordNotes = wordId.switchMap {
            if (it == null) AbsentLiveData.create()
            else repository.oldWordNotes(it)
        }
    }
}
