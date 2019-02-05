package com.rkm.tdd.shuowen.repository

import com.rkm.tdd.shuowen.db.dao.BookImageDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookImageRepository @Inject constructor(val dao: BookImageDao) {

    fun volumes() = dao.volumes()

    fun images(volume: Int) = dao.images(volume)
}
