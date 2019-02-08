package com.rkm.tdd.shuowen.repository

import com.rkm.tdd.shuowen.AppExecutors
import com.rkm.tdd.shuowen.db.dao.WordDao
import com.rkm.tdd.shuowen.db.model.Note
import com.rkm.tdd.shuowen.db.model.OldWordNote
import com.rkm.tdd.shuowen.db.model.Radical
import com.rkm.tdd.shuowen.db.model.Word
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class WordRepository @Inject constructor(val dao: WordDao, private val appExecutors: AppExecutors) {

    open fun words(search: String) = dao.words("%$search%")

    fun word(wordId: Int) = dao.word(wordId)

    fun notes(wordId: Int) = dao.notes(wordId)

    fun oldWordNotes(wordId: Int) = dao.oldWordNotes(wordId)

    fun wordIds(search: String) = dao.wordIds("%$search%")

    fun radical(wordId: Int) = dao.radical(wordId)

    fun save(word: Word) {
        appExecutors.diskIO().execute {
            dao.save(word)
        }
    }

    fun save(note: Note) {
        appExecutors.diskIO().execute {
            dao.save(note)
        }
    }

    fun save(note: OldWordNote) {
        appExecutors.diskIO().execute {
            dao.save(note)
        }
    }

    fun save(radical: Radical) {
        appExecutors.diskIO().execute {
            dao.save(radical)
        }
    }
}
