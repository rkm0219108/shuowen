package com.rkm.tdd.shuowen

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("visibleGone")
fun showHide(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("enabled")
fun enabled(view: View, enabled: Boolean) {
    view.isEnabled = enabled
}

@BindingAdapter("imageResource")
fun setImageRes(view: ImageView, resId: Int) {
    view.setImageResource(resId)
}

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, imgUrl: String) {
    Glide.with(view.context).load(imgUrl).into(view)
}

@BindingAdapter("android:selected")
fun setSelected(view: View, selected: Boolean) {
    view.isSelected = selected
}
