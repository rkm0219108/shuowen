package com.rkm.tdd.shuowen.repository

import com.rkm.tdd.shuowen.db.dao.WordDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class WordRepository @Inject constructor(val dao: WordDao) {

    open fun words(search: String) = dao.words("%$search%")

    fun word(wordId: Int) = dao.word(wordId)

    fun notes(wordId: Int) = dao.notes(wordId)

    fun oldWordNotes(wordId: Int) = dao.oldWordNotes(wordId)

    fun wordIds(search: String) = dao.wordIds("%$search%")
}
