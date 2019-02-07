package com.rkm.tdd.shuowen.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.rkm.tdd.shuowen.fragment.WordDetailFragment

class WordDetailPageAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

    var wordIds: List<Int> = listOf()

    override fun getItem(position: Int): Fragment = WordDetailFragment().apply {
        arguments = Bundle().apply {
            putInt(WordDetailFragment.EXTRA_WORD_ID, wordIds[position])
        }
    }

    override fun getCount(): Int = wordIds.size
}
