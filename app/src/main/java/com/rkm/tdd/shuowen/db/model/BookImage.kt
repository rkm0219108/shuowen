package com.rkm.tdd.shuowen.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_img")
data class BookImage(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "volume_id")
    val volumeId: Int,
    @ColumnInfo(name = "img_url")
    val imgUrl: String
)
