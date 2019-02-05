package com.rkm.tdd.shuowen.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.rkm.tdd.shuowen.db.model.BookImage

@Dao
interface BookImageDao {

    @Query("select * from img_book group by volume")
    fun volumes(): LiveData<List<BookImage>>

    @Query("select * from img_book where volume = :volume")
    fun images(volume: Int): LiveData<List<BookImage>>
}
