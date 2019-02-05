package com.rkm.tdd.shuowen.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "img_book")
data class BookImage(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "volume")
    val volume: Int,
    @ColumnInfo(name = "img_url")
    val imgUrl: String
)
