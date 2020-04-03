package com.ramonapp.tmdbmovies.extension

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions


@GlideModule
class MyGlideModule : AppGlideModule()

object ImageLoaderHelper {

    private const val CARD_CURVE_SIZE = 16

    fun load(
        context: Context?,
        url: String?,
        imageView: ImageView,
        isCurved: Boolean = false,
        isCircle: Boolean = false,
        isAbsoluteUrl: Boolean = false
    ) {
        context?.let {
            val glideReq = GlideApp.with(context)
                .load(if (isAbsoluteUrl) url else url?.toAbsoluteImageURL())
                .transition(withCrossFade())

            if (isCurved) glideReq.apply(getOptionCurve())
            if (isCircle) glideReq.apply(RequestOptions.circleCropTransform())

            glideReq.into(imageView)
        }
    }

    private fun getOptionCurve() =
        RequestOptions().transform(RoundedCorners(CARD_CURVE_SIZE.toPx()))


}