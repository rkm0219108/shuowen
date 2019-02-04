package com.rkm.tdd.shuowen.util.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T?) -> Unit) = observe(owner, Observer<T> { v -> observer.invoke(v) })

fun <X, Y> LiveData<X>.switchMap(func: (X?) -> LiveData<Y>) = Transformations.switchMap(this, func)

fun <X, Y> LiveData<X>.map(func: (X?) -> Y) = Transformations.map(this, func)
