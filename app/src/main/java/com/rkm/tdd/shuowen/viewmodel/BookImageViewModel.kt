package com.rkm.tdd.shuowen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rkm.tdd.shuowen.db.model.BookImage
import com.rkm.tdd.shuowen.repository.BookImageRepository
import com.rkm.tdd.shuowen.util.AbsentLiveData
import com.rkm.tdd.shuowen.util.ext.map
import com.rkm.tdd.shuowen.util.ext.switchMap
import javax.inject.Inject

class BookImageViewModel @Inject constructor(private val repository: BookImageRepository) : ViewModel() {

    val imageId = MutableLiveData<Int>()
    val scaleLevel: LiveData<Float>
    val image: LiveData<BookImage>

    init {
        scaleLevel = imageId.map {
            if (it == null) null
            else repository.scaleLevels[it]
        }

        image = imageId.switchMap {
            if (it == null) AbsentLiveData.create()
            else repository.image(it)
        }
    }

    fun update(scaleLevel: Float) {
        val url = imageId.value
        url ?: return
        repository.scaleLevels[url] = scaleLevel

        if (this.scaleLevel is MutableLiveData<Float>) {
            this.scaleLevel.postValue(scaleLevel)
        }
    }
}
