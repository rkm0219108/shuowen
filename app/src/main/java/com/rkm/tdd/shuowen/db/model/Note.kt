package com.rkm.tdd.shuowen.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "word_id")
    val wordId: Int,
    @ColumnInfo(name = "content")
    var content: String,
    @ColumnInfo(name = "note")
    var note: String
)
