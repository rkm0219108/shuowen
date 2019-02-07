package com.rkm.tdd.shuowen.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey
    @ColumnInfo(name = "word_id")
    val wordId: Int,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "note")
    val note: String
)
