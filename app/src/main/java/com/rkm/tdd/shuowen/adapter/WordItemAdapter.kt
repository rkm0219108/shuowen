package com.rkm.tdd.shuowen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.databinding.WordItemBinding
import com.rkm.tdd.shuowen.model.WordItem
import kotlin.properties.Delegates

class WordItemAdapter(val callback: Callback) : RecyclerView.Adapter<WordItemAdapter.ViewHolder>() {

    var items: List<WordItem> by Delegates.observable(listOf()) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition].wordId == new[newItemPosition].wordId

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition].areContentsTheSame(new[newItemPosition])
        }).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate<WordItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.word_item,
                parent,
                false
            ).apply {
                callback = this@WordItemAdapter.callback
            })

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.item = items[position]
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(val binding: WordItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface Callback {
        fun onItemClick(item: WordItem)
    }
}
