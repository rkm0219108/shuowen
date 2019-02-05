package com.rkm.tdd.shuowen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.databinding.ImageItemBinding
import com.rkm.tdd.shuowen.db.model.BookImage
import kotlin.properties.Delegates

class BookImageAdapter(val callback: Callback) : RecyclerView.Adapter<BookImageAdapter.ViewHolder>() {

    var images: List<BookImage> by Delegates.observable(listOf()) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition].imgUrl == new[newItemPosition].imgUrl

        }).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate<ImageItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.image_item,
                parent,
                false
            ).apply {
                callback = this@BookImageAdapter.callback
            })

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            image = images[position]
            executePendingBindings()
        }
    }

    inner class ViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface Callback {
        fun onImageClick(image: BookImage)
    }
}
