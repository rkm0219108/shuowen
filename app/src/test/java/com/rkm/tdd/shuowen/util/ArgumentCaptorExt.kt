package com.rkm.tdd.shuowen.util

import org.mockito.ArgumentCaptor

inline fun <reified T : Any> argumentCaptor(): ArgumentCaptor<T> {
    return ArgumentCaptor.forClass(T::class.java)
}
