package com.ramonapp.tmdbmovies.customUI

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerView : RecyclerView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val defaultPageSize: Int = 20
    private var page = 1
    private var pageSize = defaultPageSize

    private var visibleItemCount = 0
    private var firstVisibleItem = 0
    private var totalItemCount = 0

    private var state = ListState.Idle
    private var pageChange: ((newPage: Int) -> Unit)? = null

    init {
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                (layoutManager as? LinearLayoutManager)?.apply {
                    firstVisibleItem = findFirstVisibleItemPosition()
                    visibleItemCount = childCount
                    totalItemCount = itemCount
                }

                if (
                    state == ListState.Idle
                    && (visibleItemCount + firstVisibleItem) >= totalItemCount
                    && firstVisibleItem >= 0
                    && totalItemCount >= pageSize
                ) {
                    state = ListState.Pending
                    pageChange?.invoke(++page)
                }

            }
        })

    }

    fun setOnPageChange(pageChange: ((newPage: Int) -> Unit)) {
        this.pageChange = pageChange
    }

    fun getPageSize() = pageSize

    fun setState(state: ListState) {
        this.state = state
    }

}

enum class ListState {
    Idle,
    Pending,
    End
}