package com.rkm.tdd.shuowen.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.rkm.tdd.shuowen.fragment.BookImageFragment

class BookImagePageAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

    var imageIds: List<Int> = listOf()

    override fun getItem(position: Int): Fragment = BookImageFragment().apply {
        arguments = Bundle().apply {
            putInt(BookImageFragment.EXTRA_IMAGE_ID, imageIds[position])
        }
    }

    override fun getCount(): Int = imageIds.size
}
