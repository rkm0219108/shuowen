package com.rkm.tdd.shuowen.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "word")
    val word: String,
    @ColumnInfo(name = "radical_id")
    val radicalId: Int,
    @ColumnInfo(name = "radical")
    val radical: String,
    @ColumnInfo(name = "img_url")
    val imgUrl: String,
    @ColumnInfo(name = "old_word")
    val oldWord: String,
    @ColumnInfo(name = "old_img_url")
    val oldImgUrl: String,
    @ColumnInfo(name = "content")
    val content: String
)
