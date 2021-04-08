package com.aldredo.home.presentation.view

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aldredo.home.R
import com.aldredo.home.presentation.adapter.*
import com.aldredo.home.presentation.mvvm.HomeViewModel

class RootView(
    private val viewModel: HomeViewModel, owner: LifecycleOwner
) {
    private var rootView: View? = null
    private val actionAdapter = ActionAdapter(viewModel)
    private val newsAdapter = NewsAdapter(viewModel)
    private val titleAdapter = TitleAdapter()
    private val statusAdapter = StatusOrderAdapter()
    private val footerAdapter = FooterAdapter()

    init {
        viewModel.getHomeData().observe(owner, Observer {
            actionAdapter.submitList(it[1].action)
            newsAdapter.submitList(it[0].news)
        })

        viewModel.getErrorMessage().observe(owner, Observer {
            showMessageError(it)
        })
    }

    private fun showMessageError(it: String?) {
        Toast.makeText(rootView?.context, it, Toast.LENGTH_LONG).show()
    }

    private fun initView() = rootView?.apply {
        findViewById<RecyclerView>(R.id.recycler_view)?.apply {
            layoutManager = LinearLayoutManager(rootView?.context)
            adapter = ConcatAdapter(
                actionAdapter,
                statusAdapter,
                titleAdapter,
                newsAdapter,
                footerAdapter
            )
        }
        findViewById<SwipeRefreshLayout>(R.id.swipe_refresh).apply {
            setOnRefreshListener {
                isRefreshing = false
                viewModel.getActionOrNews()
            }
        }
    }


    fun inflateView(rootView: View) {
        this.rootView = rootView
        initView()
    }
}