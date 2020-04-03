package com.ramonapp.tmdbmovies.home

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ramonapp.tmdbmovies.R
import com.ramonapp.tmdbmovies.core.base.BaseActivity
import com.ramonapp.tmdbmovies.customUI.ListState
import com.ramonapp.tmdbmovies.detail.ARG_MOVIE_ID
import com.ramonapp.tmdbmovies.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var movieAdapter: MoviesAdapter? = null

    private var fromDate : String? = null
    private var toData : String? = null


    override fun getContentView() = R.layout.activity_main

    override fun initView() {
        movieAdapter = MoviesAdapter(this) {
            startActivity(Intent(this, DetailActivity::class.java).apply {
                putExtra(ARG_MOVIE_ID, it)
            })
        }
        movieRcl.adapter = movieAdapter
        movieRcl.layoutManager = GridLayoutManager(this, 2)
        movieRcl.setHasFixedSize(true)
        movieRcl.setOnPageChange {
            homeViewModel.getMovies(it, fromDate, toData)
            swipeRefreshLyt.isRefreshing = true
        }

        swipeRefreshLyt.setOnRefreshListener {
            movieAdapter?.clear()
            homeViewModel.getMovies()
            fromDate = null
            toData = null
        }

        fromNp.minValue = 1950
        toNp.minValue = 1950
        fromNp.maxValue = 2020
        toNp.maxValue = 2020
        fromNp.wrapSelectorWheel = false
        toNp.wrapSelectorWheel = false
        toNp.value = 2020
        BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_HIDDEN
    }

    override fun clickListeners() {
        fab.setOnClickListener {
            BottomSheetBehavior.from(bottomSheet)?.apply {
                state = if (state == BottomSheetBehavior.STATE_HIDDEN)
                    BottomSheetBehavior.STATE_EXPANDED
                else
                    BottomSheetBehavior.STATE_HIDDEN
            }
        }

        applyFilterBtn.setOnClickListener {
            movieAdapter?.clear()
            fromDate = "${fromNp.value}-01-01"
            toData = "${toNp.value}-12-30"
            homeViewModel.getMovies(from = fromDate , to = toData)
            BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    override fun subscribeViews() {
        val owner = this
        homeViewModel.apply {

            movieList.observe(owner, Observer {
                if (it.isNullOrEmpty()) {
                    movieRcl.setState(ListState.End)
                } else {
                    movieAdapter?.addAll(it)
                    movieRcl.setState(ListState.Idle)
                }
                swipeRefreshLyt.isRefreshing = false
            })

            mError.observe(owner, Observer {
                movieRcl.setState(ListState.End)
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
            })
        }
    }

    override fun executeInitialTasks() {
        swipeRefreshLyt.isRefreshing = true
        homeViewModel.getMovies()
    }

}

