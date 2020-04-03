package com.ramonapp.tmdbmovies.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getContentView()?.let {
            setContentView(it)
            initView()
            subscribeViews()
            clickListeners()
            executeInitialTasks()
        } ?:kotlin.run { throw Exception("Layout not defined") }
    }


    abstract fun getContentView(): Int?
    abstract fun initView()
    abstract fun clickListeners()
    abstract fun subscribeViews()
    abstract fun executeInitialTasks()
}
