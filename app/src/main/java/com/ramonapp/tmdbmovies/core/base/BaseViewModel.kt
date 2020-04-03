package com.ramonapp.tmdbmovies.core.base

import androidx.lifecycle.ViewModel
import com.ramonapp.tmdbmovies.core.mvvm.SingleLiveEvent


open class BaseViewModel : ViewModel() {

    var mError = SingleLiveEvent<String>()

}