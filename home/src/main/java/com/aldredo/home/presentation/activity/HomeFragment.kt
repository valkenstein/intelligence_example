package com.aldredo.home.presentation.activity

import android.os.Bundle
import android.view.View
import com.aldredo.core.base.fragment.BaseFragment
import com.aldredo.home.R
import com.aldredo.home.presentation.mvvm.HomeViewModel
import com.aldredo.home.presentation.view.RootView
import com.aldredo.home.di.HomeComponent
import com.aldredo.home.navigation.Router
import javax.inject.Inject

class HomeFragment : BaseFragment(R.layout.activity_home) {
    @Inject
    lateinit var lifeModel: HomeViewModel


    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeComponent.create(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RootView(lifeModel, this).apply {
            inflateView(view)
            lifeModel.getActionOrNews()
        }
    }

    override fun onResume() {
        super.onResume()
        router.attachContext(this)
    }

    override fun onStop() {
        super.onStop()
        router.detachContext()
    }
}

