package com.rkm.tdd.shuowen.repository

import androidx.lifecycle.LiveData
import com.rkm.tdd.shuowen.db.dao.BookImageDao
import com.rkm.tdd.shuowen.db.model.BookImage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookImageRepository @Inject constructor(val dao: BookImageDao) {

    fun volumes(): LiveData<List<BookImage>> = dao.volumes()
}
