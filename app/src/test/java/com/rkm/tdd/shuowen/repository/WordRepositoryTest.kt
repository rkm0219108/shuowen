package com.rkm.tdd.shuowen.repository

import com.rkm.tdd.shuowen.db.dao.WordDao
import com.rkm.tdd.shuowen.util.AbsentLiveData
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class WordRepositoryTest {

    private lateinit var dao: WordDao
    private lateinit var repository: WordRepository

    @Before
    fun setup() {
        dao = mock(WordDao::class.java)
        repository = WordRepository(dao)
    }

    @Test
    fun testNull() {
        `when`(dao.words(anyString())).thenReturn(AbsentLiveData.create(listOf()))
        repository.wordList("")
        verify(dao, times(1)).words(anyString())
    }

    @Test
    fun testSearch() {
        `when`(dao.words(anyString())).thenReturn(AbsentLiveData.create(listOf()))
        val search = ""
        repository.wordList(search)
//        argumentCaptor<String>().apply {
//            verify(dao, times(1)).words(capture())
//            assertThat(value, `is`(search))
//        }
    }
}
