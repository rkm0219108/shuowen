package com.rkm.tdd.shuowen.model

class WordItem {

    var word = "Word"

    fun areContentsTheSame(item: WordItem): Boolean = word == item.word
}
