package com.rkm.tdd.shuowen.repository

import androidx.lifecycle.LiveData
import com.rkm.tdd.shuowen.db.model.Word
import com.rkm.tdd.shuowen.util.AbsentLiveData

open class WordRepository {
    open fun wordList(search: String): LiveData<List<Word>> {
        return AbsentLiveData.create(emptyList())
    }
}
