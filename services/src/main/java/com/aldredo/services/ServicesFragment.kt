package com.aldredo.services

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aldredo.services.di.ServicesComponent
import com.aldredo.services.presentation.ServicesViewModel
import com.aldredo.services.presentation.adapter.CategoryAdapter
import com.aldredo.services.presentation.adapter.PriceAdapter
import javax.inject.Inject


class ServicesFragment : Fragment(R.layout.fragment_services) {
    @Inject
    lateinit var priceAdapter: PriceAdapter

    @Inject
    lateinit var categoryAdapter: CategoryAdapter

    @Inject
    lateinit var viewModel: ServicesViewModel


    val m = MutableLiveData<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ServicesComponent.create(this).inject(this)
        viewModel.init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recycler_category_view).apply {
            layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
            adapter = categoryAdapter
        }
        view.findViewById<RecyclerView>(R.id.recycler_price_view).apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = priceAdapter
        }

        viewModel.getCategoryData().observe(viewLifecycleOwner, {
            categoryAdapter.submitList(it)
            categoryAdapter.notifyDataSetChanged()
        })

        viewModel.getCurrentPriceData().observe(viewLifecycleOwner, {
            priceAdapter.submitList(it)
        })

        viewModel.getErrorMessages().observe(viewLifecycleOwner, {
            showToast(it)
        })
    }

    override fun onResume() {
        super.onResume()
    }

    private fun showToast(it: String) {
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()
        viewModel.clear()
    }
}