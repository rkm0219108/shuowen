package com.rkm.tdd.shuowen.util

import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlin.math.max
import kotlin.math.min

class SquareCenterCrop : CenterCrop() {

    var resizeListener: ResizeListener? = null

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val outSize = max(outWidth, outHeight)
        val min = min(outWidth, outHeight)

        val medium = outSize.toFloat() / min
        resizeListener?.onResized(medium)

        return super.transform(pool, toTransform, outSize, outSize)
    }

    interface ResizeListener {
        fun onResized(medium: Float)
    }
}
