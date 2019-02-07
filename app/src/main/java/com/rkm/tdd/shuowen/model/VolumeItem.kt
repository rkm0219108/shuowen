package com.rkm.tdd.shuowen.model

import com.rkm.tdd.shuowen.db.model.BookVolume

class VolumeItem(bookVolume: BookVolume) {

    val volumeId = bookVolume.id
    val volume = bookVolume.volume

    fun displayName() = "第${volume}卷"

    fun areContentsTheSame(item: VolumeItem): Boolean = volume == item.volume
}
