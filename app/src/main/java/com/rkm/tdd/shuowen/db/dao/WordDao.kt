package com.rkm.tdd.shuowen.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.rkm.tdd.shuowen.db.model.Note
import com.rkm.tdd.shuowen.db.model.OldWordNote
import com.rkm.tdd.shuowen.db.model.Radical
import com.rkm.tdd.shuowen.db.model.Word

@Dao
interface WordDao {
    @Query("select words.id, word, radicals.id as radical_id, img_url, old_word, old_img_url, content from words left join radicals on words.radical_id = radicals.id where word like :search or radicals.radical like :search")
    fun words(search: String): LiveData<List<Word>>

    @Query("select words.id, word, radicals.id as radical_id, img_url, old_word, old_img_url, content from words left join radicals on words.radical_id = radicals.id where words.id = :wordId")
    fun word(wordId: Int): LiveData<Word>

    @Update(onConflict = REPLACE)
    fun save(word: Word)

    @Query("select words.id from words left join radicals on words.radical_id = radicals.id where word like :search or radicals.radical like :search")
    fun wordIds(search: String): LiveData<List<Int>>

    @Query("select * from radicals")
    fun radicals(): LiveData<List<Radical>>

    @Query("select * from radicals where id = :radicalId")
    fun radical(radicalId: Int): LiveData<Radical>

    @Update(onConflict = REPLACE)
    fun save(radical: Radical)

    @Query("select words.id, word, radicals.id as radical_id, img_url, old_word, old_img_url, content from words left join radicals on words.radical_id = radicals.id where words.radical_id = :radicalId")
    fun words(radicalId: Int): LiveData<List<Word>>

    @Query("select * from notes where word_id = :wordId")
    fun notes(wordId: Int): LiveData<List<Note>>

    @Update(onConflict = REPLACE)
    fun save(note: Note)

    @Query("select * from old_word_notes where word_id = :wordId")
    fun oldWordNotes(wordId: Int): LiveData<List<OldWordNote>>

    @Update(onConflict = REPLACE)
    fun save(note: OldWordNote)
}
