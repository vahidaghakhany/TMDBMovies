package com.ramonapp.tmdbmovies.detail

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramonapp.tmdbmovies.R
import com.ramonapp.tmdbmovies.core.base.BaseActivity
import com.ramonapp.tmdbmovies.customUI.ListState
import com.ramonapp.tmdbmovies.extension.ImageLoaderHelper
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

const val ARG_MOVIE_ID = "movieId"

class DetailActivity : BaseActivity() {

    private val detailViewModel: DetailViewModel by viewModel()

    private var movieId: Long = -1

    override fun getContentView() = R.layout.activity_detail

    override fun initView() {
        movieId = intent.getLongExtra(ARG_MOVIE_ID, -1)
    }

    override fun clickListeners() {
    }

    override fun subscribeViews() {
        val owner = this
        detailViewModel.apply {

            movie.observe(owner, Observer {
                it?.apply {
                    titleTxt.text = title
                    descTxt.text = overview
                    rateTxt.text = voteAverage?.toString()
                    dateTxt.text = releaseDate?.substring(0,4)
                    ImageLoaderHelper.load(this@DetailActivity, backdropPath, backDropImg)
                    ImageLoaderHelper.load(this@DetailActivity, posterPath, coverImg, isCurved = true)
                }

            })

            mError.observe(owner, Observer {
                Toast.makeText(this@DetailActivity, it, Toast.LENGTH_LONG).show()
                finish()
            })
        }
    }

    override fun executeInitialTasks() {
        detailViewModel.getMovie(movieId)
    }

}

