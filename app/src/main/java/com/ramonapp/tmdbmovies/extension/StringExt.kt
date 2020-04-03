package com.ramonapp.tmdbmovies.extension

import com.ramonapp.tmdbmovies.BuildConfig




fun String.toFaNumber(): String {
    val faNumbers = listOf("۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹")
    return replace(Regex("[0-9]")){
        faNumbers[it.value.toInt()]
    }
}

fun String.toAbsoluteImageURL() = BuildConfig.IMAGE_URL + this