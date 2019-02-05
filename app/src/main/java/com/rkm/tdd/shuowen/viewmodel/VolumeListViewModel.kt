package com.rkm.tdd.shuowen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rkm.tdd.shuowen.model.VolumeItem
import com.rkm.tdd.shuowen.repository.BookImageRepository
import com.rkm.tdd.shuowen.util.ext.map
import javax.inject.Inject

class VolumeListViewModel @Inject constructor(repository: BookImageRepository): ViewModel() {

    val volumes: LiveData<List<VolumeItem>>

    init {
        volumes = repository.volumes().map { list ->
            list?.map { VolumeItem(it) } ?: listOf()
        }
    }
}
