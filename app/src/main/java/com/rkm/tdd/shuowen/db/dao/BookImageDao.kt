package com.rkm.tdd.shuowen.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.rkm.tdd.shuowen.db.model.BookImage
import com.rkm.tdd.shuowen.db.model.BookVolume

@Dao
interface BookImageDao {

    @Query("select * from book_volume")
    fun volumes(): LiveData<List<BookVolume>>

    @Query("select * from book_img where volume_id = :volumeId")
    fun images(volumeId: Int): LiveData<List<BookImage>>

    @Query("select id from book_img where volume_id = :volumeId")
    fun imageIds(volumeId: Int): LiveData<List<Int>>

    @Query("select * from book_volume where id = :volumeId")
    fun volume(volumeId: Int): LiveData<BookVolume>

    @Query("select * from book_img where id = :imageId")
    fun image(imageId: Int): LiveData<BookImage>
}
