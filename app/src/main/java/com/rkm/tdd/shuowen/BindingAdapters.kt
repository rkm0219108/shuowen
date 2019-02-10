package com.rkm.tdd.shuowen

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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
fun setImageUrl(view: ImageView, imgUrl: String?) {
    if (imgUrl?.isEmpty() == true) return
    Glide.with(view.context).load(imgUrl).apply(RequestOptions.placeholderOf(R.drawable.progress_animation)).into(view)
}

@BindingAdapter("android:selected")
fun setSelected(view: View, selected: Boolean) {
    view.isSelected = selected
}
