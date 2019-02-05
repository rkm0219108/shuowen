package com.rkm.tdd.shuowen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.databinding.VolumeItemBinding
import com.rkm.tdd.shuowen.model.VolumeItem
import kotlin.properties.Delegates

class VolumeItemAdapter(val callback: Callback) : RecyclerView.Adapter<VolumeItemAdapter.ViewHolder>() {

    var items: List<VolumeItem> by Delegates.observable(listOf()) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition].areContentsTheSame(new[newItemPosition])
        }).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate<VolumeItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.volume_item,
                parent,
                false
            ).apply {
                callback = this@VolumeItemAdapter.callback
            })

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.item = items[position]
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(val binding: VolumeItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface Callback {
        fun onItemClick(item: VolumeItem)
    }
}
