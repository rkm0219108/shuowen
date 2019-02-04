package com.rkm.tdd.shuowen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rkm.tdd.shuowen.model.WordItem
import com.rkm.tdd.shuowen.repository.WordRepository
import com.rkm.tdd.shuowen.util.AbsentLiveData
import com.rkm.tdd.shuowen.util.ext.map
import com.rkm.tdd.shuowen.util.ext.switchMap
import javax.inject.Inject

class MainViewModel @Inject constructor(repository: WordRepository) : ViewModel() {

    val search = MutableLiveData<String>()
    val wordItems: LiveData<List<WordItem>>

    init {
        wordItems = search.switchMap { search ->
            if (search == null) {
                return@switchMap AbsentLiveData.create(emptyList<WordItem>())
            }
            repository.wordList(search).map { words ->
                if (words == null) {
                    return@map emptyList<WordItem>()
                }
                words.map { WordItem(it) }
            }
        }
    }
}
