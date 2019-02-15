package com.rkm.tdd.shuowen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.databinding.BookImageFragmentBinding
import com.rkm.tdd.shuowen.di.Injectable
import com.rkm.tdd.shuowen.util.AutoClearedValue
import com.rkm.tdd.shuowen.util.SquareCenterCrop
import com.rkm.tdd.shuowen.util.ext.observe
import com.rkm.tdd.shuowen.viewmodel.BookImageViewModel
import javax.inject.Inject

class BookImageFragment : Fragment(), Injectable {

    companion object {
        const val EXTRA_IMAGE_ID = "image_id"
    }

    private lateinit var binding: AutoClearedValue<BookImageFragmentBinding>

    @Inject lateinit var factory: ViewModelProvider.Factory

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this, factory).get(BookImageViewModel::class.java)
        viewModel.scaleLevel.observe(this) {
            it ?: return@observe

            binding.get()?.image?.let { image ->
                image.mediumScale = it
            }
        }

        viewModel.image.observe(this) {
            it ?: return@observe

            binding.get()?.image?.let { image ->
                Glide.with(this).load(it.imgUrl)
                    .apply(placeholderOf(R.drawable.progress_animation).transform(SquareCenterCrop().apply {
                        resizeListener = object : SquareCenterCrop.ResizeListener {
                            override fun onResized(medium: Float) {
                                viewModel.update(medium)
                            }
                        }
                    })).into(image)
            }
        }

        viewModel.imageId.value = arguments?.getInt(EXTRA_IMAGE_ID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        DataBindingUtil.inflate<BookImageFragmentBinding>(
            inflater,
            R.layout.book_image_fragment,
            container,
            false
        ).apply {
            binding = AutoClearedValue(this@BookImageFragment, this)
        }.root
}
