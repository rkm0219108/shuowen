package com.rkm.tdd.shuowen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rkm.tdd.shuowen.db.model.BookVolume
import com.rkm.tdd.shuowen.repository.BookImageRepository
import com.rkm.tdd.shuowen.util.AbsentLiveData
import com.rkm.tdd.shuowen.util.ext.switchMap
import javax.inject.Inject

class BookImageListViewModel @Inject constructor(repository: BookImageRepository): ViewModel() {

    val volumeId = MutableLiveData<Int>()
    val imageIds: LiveData<List<Int>>
    val volume: LiveData<BookVolume>

    init {
        imageIds = volumeId.switchMap {
            if (it == null) AbsentLiveData.create()
            else repository.imageIds(it)
        }

        volume = volumeId.switchMap {
            if (it == null) AbsentLiveData.create()
            else repository.volume(it)
        }
    }
}
