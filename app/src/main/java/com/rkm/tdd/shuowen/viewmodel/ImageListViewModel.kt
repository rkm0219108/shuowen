package com.rkm.tdd.shuowen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rkm.tdd.shuowen.db.model.BookImage
import com.rkm.tdd.shuowen.repository.BookImageRepository
import com.rkm.tdd.shuowen.util.AbsentLiveData
import com.rkm.tdd.shuowen.util.ext.switchMap
import javax.inject.Inject

class ImageListViewModel @Inject constructor(repository: BookImageRepository): ViewModel() {

    val volume = MutableLiveData<Int>()
    val images: LiveData<List<BookImage>>

    init {
        images = volume.switchMap {
            if (it == null) AbsentLiveData.create(listOf())
            else repository.images(it)
        }
    }
}
