package com.rkm.tdd.shuowen.model

import com.rkm.tdd.shuowen.db.model.BookImage

class VolumeItem(bookImage: BookImage) {

    val volume = bookImage.volume

    fun displayName() = "第${volume}卷"

    fun areContentsTheSame(item: VolumeItem): Boolean = volume == item.volume
}
