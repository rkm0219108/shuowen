package com.rkm.tdd.shuowen.repository

import com.rkm.tdd.shuowen.db.dao.BookImageDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookImageRepository @Inject constructor(val dao: BookImageDao) {

    val scaleLevels = mutableMapOf<Int, Float>()

    fun volumes() = dao.volumes()

    fun images(volumeId: Int) = dao.images(volumeId)

    fun volume(volumeId: Int) = dao.volume(volumeId)

    fun imageIds(volumeId: Int) = dao.imageIds(volumeId)

    fun image(imageId: Int) = dao.image(imageId)
}
