package com.aldredo.profile_person.presentation.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aldredo.profile_person.R
import com.aldredo.profile_person.di.ProfileComponent
import com.aldredo.profile_person.mvvm.HistoryViewModel
import com.aldredo.profile_person.presentation.adapter.HistoryAdapter
import javax.inject.Inject

class OrderHistoryFragment : Fragment(R.layout.fragment_order_history) {
    @Inject
    lateinit var viewModel: HistoryViewModel
    private val historyAdapter = HistoryAdapter().apply {
        defaultCount = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ProfileComponent.create(this).inject(this)
        viewModel.apply {
            init()
            getDataHistoryOrder().observe(viewLifecycleOwner, {
                historyAdapter.submitList(it)
                historyAdapter.notifyDataSetChanged()
            })
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        view?.findViewById<RecyclerView>(R.id.recycler_view)?.apply {
            this.adapter = historyAdapter
            layoutManager = LinearLayoutManager(rootView?.context)
        }
    }

    companion object {
        @JvmStatic
        fun startActivity(activity: Context, bundle: Bundle) {
            val intent = Intent(activity, OrderHistoryFragment::class.java)
            intent.putExtras(bundle)
            activity.startActivity(intent)
        }
    }
}