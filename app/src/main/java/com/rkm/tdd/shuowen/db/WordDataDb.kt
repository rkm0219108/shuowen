package com.rkm.tdd.shuowen.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rkm.tdd.shuowen.db.dao.WordDao
import com.rkm.tdd.shuowen.db.model.Note
import com.rkm.tdd.shuowen.db.model.OldWordNote
import com.rkm.tdd.shuowen.db.model.Radical
import com.rkm.tdd.shuowen.db.model.Word

@Database(
    entities = [
        Word::class,
        Radical::class,
        Note::class,
        OldWordNote::class
    ], version = 1
)
abstract class WordDataDb : RoomDatabase() {
    abstract fun wordDao(): WordDao
}
