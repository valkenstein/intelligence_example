package com.aldredo.mainn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aldredo.core.base.fragment.BaseFragment
import com.aldredo.core.base.navigation.IBottomNavManager
import com.aldredo.mainn.di.MainComponent
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var bottomNavManager: IBottomNavManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainComponent.create(this)
        setupNavigationManager()

    }

    private fun setupNavigationManager() {
        bottomNavManager.init(
            supportFragmentManager,
            R.id.body_fragment,
            findViewById(R.id.bottom_navigation)
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        bottomNavManager.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        bottomNavManager.onRestoreInstanceState(savedInstanceState)
        setupNavigationManager()
    }

    override fun onBackPressed() {
        if (!bottomNavManager.onBackPressed()) super.onBackPressed()
    }
}