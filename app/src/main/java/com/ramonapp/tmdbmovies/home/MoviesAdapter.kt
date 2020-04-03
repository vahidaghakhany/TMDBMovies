package com.ramonapp.tmdbmovies.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ramonapp.tmdbmovies.R
import com.ramonapp.tmdbmovies.data.MovieModel
import com.ramonapp.tmdbmovies.extension.ImageLoaderHelper
import kotlinx.android.synthetic.main.movie_row.view.*

class MoviesAdapter(
    private val mContext: Context?,
    private val itemSelect: ((id: Long?) -> Unit)? = null
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val mList = mutableListOf<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_row, parent, false)

        view.setOnClickListener {
            itemSelect?.apply {
                (it.tag as? Long)?.let {
                    invoke(it)
                }
            }
        }
        return ViewHolder(view)
    }

    fun clear() {
        mList.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: List<MovieModel>) {
        mList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: MovieModel?) {
            movie?.let {
                ImageLoaderHelper.load(mContext, it.posterPath, view.coverImg)
                view.tag = it.id
            }
        }
    }
}