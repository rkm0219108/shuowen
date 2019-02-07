package com.rkm.tdd.shuowen.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class AutoClearedValue<T>(fragment: Fragment, private var value: T?) {

    init {
        val fragmentManager = fragment.fragmentManager
        fragmentManager?.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
                if (f === fragment) {
                    this@AutoClearedValue.value = null
                    fragmentManager.unregisterFragmentLifecycleCallbacks(this)
                }
            }
        }, false)
    }

    fun get(): T? {
        return value
    }
}