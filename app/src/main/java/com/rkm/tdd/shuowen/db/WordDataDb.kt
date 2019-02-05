package com.rkm.tdd.shuowen.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rkm.tdd.shuowen.db.dao.BookImageDao
import com.rkm.tdd.shuowen.db.dao.WordDao
import com.rkm.tdd.shuowen.db.model.*

@Database(
    entities = [
        Word::class,
        Radical::class,
        Note::class,
        OldWordNote::class,
        BookImage::class
    ], version = 1
)
abstract class WordDataDb : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun bookImageDao(): BookImageDao
}
