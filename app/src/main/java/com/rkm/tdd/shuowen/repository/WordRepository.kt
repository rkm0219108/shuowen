package com.rkm.tdd.shuowen.repository

import androidx.lifecycle.LiveData
import com.rkm.tdd.shuowen.db.dao.WordDao
import com.rkm.tdd.shuowen.db.model.Word
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class WordRepository @Inject constructor(val dao: WordDao) {

    open fun wordList(search: String): LiveData<List<Word>> {
        return dao.words("%$search%")
    }
}
