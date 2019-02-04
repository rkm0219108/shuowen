package com.rkm.tdd.shuowen.model

import com.rkm.tdd.shuowen.db.model.Word

class WordItem(word: Word) {

    val word = word.word

    fun areContentsTheSame(item: WordItem): Boolean = word == item.word
}
