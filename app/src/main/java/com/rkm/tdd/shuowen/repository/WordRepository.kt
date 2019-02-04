package com.rkm.tdd.shuowen.repository

import androidx.lifecycle.LiveData
import com.rkm.tdd.shuowen.db.model.Word
import com.rkm.tdd.shuowen.util.AbsentLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class WordRepository @Inject constructor() {

    open fun wordList(search: String): LiveData<List<Word>> {
        return AbsentLiveData.create(listOf(Word()))
    }
}
