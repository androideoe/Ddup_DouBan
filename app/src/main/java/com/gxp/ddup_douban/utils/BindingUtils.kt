package com.gxp.ddup_douban.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created by ddup on 2020/11/16.
 */

class BindingUtils {


    companion object {

        @BindingAdapter("app:imgUrl")
        @JvmStatic
        fun load(view: ImageView, url: String) {
            Glide.with(view.context).load(url).into(view)
        }
    }
}
