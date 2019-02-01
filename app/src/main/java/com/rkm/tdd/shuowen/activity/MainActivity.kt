package com.rkm.tdd.shuowen.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.adapter.WordItemAdapter
import com.rkm.tdd.shuowen.model.WordItem
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val adapter = WordItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        word_list.adapter = adapter
        adapter.items = listOf(WordItem())
    }
}
