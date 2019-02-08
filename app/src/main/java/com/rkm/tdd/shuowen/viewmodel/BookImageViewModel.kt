package com.rkm.tdd.shuowen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rkm.tdd.shuowen.repository.BookImageRepository
import com.rkm.tdd.shuowen.util.ext.map
import javax.inject.Inject

class BookImageViewModel @Inject constructor(val repository: BookImageRepository) : ViewModel() {

    val imageUrl = MutableLiveData<String>()
    val scaleLevel: LiveData<Float>

    init {
        scaleLevel = imageUrl.map {
            if (it == null) null
            else repository.scaleLevels[it]
        }
    }

    fun update(scaleLevel: Float) {
        val url = imageUrl.value
        url ?: return
        repository.scaleLevels[url] = scaleLevel

        if (this.scaleLevel is MutableLiveData<Float>) {
            this.scaleLevel.postValue(scaleLevel)
        }
    }
}
