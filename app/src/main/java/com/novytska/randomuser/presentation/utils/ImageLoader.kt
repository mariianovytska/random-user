package com.novytska.randomuser.presentation.utils

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class ImageLoader(val context: Context) {
    fun loadCircleImage(
        url: String?,
        target: ImageView
    ) {
        Picasso.with(context)
            .load(url)
            .transform(CropCircleTransformation())
            .into(target)
    }
}