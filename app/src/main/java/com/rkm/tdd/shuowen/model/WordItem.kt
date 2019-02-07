package com.rkm.tdd.shuowen.model

import com.rkm.tdd.shuowen.db.model.Word

class WordItem(word: Word) {

    val word = word.word
    val content = word.content
    val imgUrl = word.imgUrl
    val wordId = word.id

    fun areContentsTheSame(item: WordItem): Boolean = word == item.word && content == item.content
}
