package com.rkm.tdd.shuowen.repository

import com.rkm.tdd.shuowen.AppExecutors
import com.rkm.tdd.shuowen.db.dao.WordDao
import com.rkm.tdd.shuowen.util.AbsentLiveData
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WordRepositoryTest {

    @Mock
    private lateinit var dao: WordDao
    private lateinit var repository: WordRepository

    @Before
    fun setup() {
        repository = WordRepository(dao, AppExecutors())
    }

    @Test
    fun testNull() {
        `when`(dao.words(anyString())).thenReturn(AbsentLiveData.create(listOf()))
        repository.words("")
        verify(dao, times(1)).words(anyString())
    }

    @Test
    fun testSearch() {
        `when`(dao.words(anyString())).thenReturn(AbsentLiveData.create(listOf()))
        val search = ""
        repository.words(search)
//        argumentCaptor<String>().apply {
//            verify(dao, times(1)).words(capture())
//            assertThat(value, `is`(search))
//        }
    }
}
