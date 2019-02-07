package com.rkm.tdd.shuowen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rkm.tdd.shuowen.db.model.Word
import com.rkm.tdd.shuowen.repository.WordRepository
import com.rkm.tdd.shuowen.util.AbsentLiveData
import com.rkm.tdd.shuowen.util.ext.switchMap
import javax.inject.Inject

class WordDetailListViewModel @Inject constructor(repository: WordRepository) : ViewModel() {

    val search = MutableLiveData<String>()
    val wordIds: LiveData<List<Int>>

    val wordId = MutableLiveData<Int>()
    val word: LiveData<Word>

    init {
        wordIds = search.switchMap {
            if (it == null) AbsentLiveData.create(listOf())
            else repository.wordIds(it)
        }

        word = wordId.switchMap {
            if (it == null) AbsentLiveData.create()
            else repository.word(it)
        }
    }
}
