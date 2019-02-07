package com.rkm.tdd.shuowen.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rkm.tdd.shuowen.model.WordItem
import com.rkm.tdd.shuowen.repository.WordRepository
import com.rkm.tdd.shuowen.util.AbsentLiveData
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var repository: WordRepository
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        repository = mock(WordRepository::class.java)
        `when`(repository.words(anyString())).thenReturn(AbsentLiveData.create(listOf()))
        viewModel = MainViewModel(repository)
    }

    @Test
    fun testNull() {
        assertThat(viewModel.wordItems, notNullValue())
        verify(repository, never()).words(anyString())
    }

    @Test
    fun dontFetchWithoutObservers() {
        viewModel.search.postValue("")
        verify(repository, never()).words(anyString())
    }

    @Test
    fun fetchWhenObserved() {
        val word = "a"
        viewModel.search.postValue(word)
        viewModel.wordItems.observeForever(mock(Observer::class.java) as Observer<in List<WordItem>>)

        /*argumentCaptor<String>().apply {
            verify(repository, times(1)).words(capture())
            assertThat(value, `is`(word))
        }*/
    }
}
