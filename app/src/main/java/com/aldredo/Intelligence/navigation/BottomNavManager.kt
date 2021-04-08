package com.aldredo.Intelligence.navigation

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.forEach
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.aldredo.Intelligence.R
import com.aldredo.core.base.scoupe.ApplicationScope
import com.aldredo.core.base.navigation.IBottomNavManager
import com.aldredo.core.base.util.IManagerToken
import com.aldredo.core.base.util.ObserverChangeToken
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

/**
 * Created by Vipul Asri on 15/07/20.
 * Ported from: https://github.com/android/architecture-components-samples/blob/master/NavigationAdvancedSample
 * Manages the various graphs needed for a [BottomNavigationView].
 *
 * This sample is a workaround until the Navigation Component supports multiple back stacks.
 */

@ApplicationScope
class BottomNavManager @Inject constructor(managerToken: IManagerToken) : IBottomNavManager,
    ObserverChangeToken {
    private lateinit var fragmentManager: FragmentManager
    private var containerId: Int = 0
    private lateinit var bottomNavigationView: BottomNavigationView
    private var isAuthorized = false

    companion object {
        private const val KEY_NAV_HISTORY = "nav_history"
    }

    init {
        managerToken.subscribe(this)
    }

    override fun tokenChange(isAuthorization: Boolean) {
        navGraphIds =
            if (isAuthorization) getGraphIdAuthorization() else getGraphIdNotAuthorization()

        isAuthorized = isAuthorization
        setupNavController()

        //bottomNavigationView.selectedItemId = R.id.profile_person
        //clickMenuItem(bottomNavigationView.menu.getItem(3))
        //navGraphStartDestinations[navHostFragmentIdList[navHostFragmentIdList.size - 1]] = navHostFragment.navController.graph.startDestination
        //bottomNavigationView.selectedItemId = R.id.home_navigation
    }

    private fun getGraphIdAuthorization() = listOf(
        R.navigation.home_navigation,
        R.navigation.services_navigation,
        R.navigation.notification_navigation,
        R.navigation.profile_person
    )

    private fun getGraphIdNotAuthorization() = listOf(
        R.navigation.home_navigation,
        R.navigation.services_navigation,
        R.navigation.notification_navigation,
        R.navigation.authorization_navigation
    )

    // Graph Id's of the tabs
    private var navGraphIds = getGraphIdNotAuthorization()

    // Map of tags
    private val graphIdToTagMap = mutableMapOf<String, String>()

    // holds the start destination of all the graphs with their bottomNavigationView item id, used for back press
    private var navGraphStartDestinations = mutableMapOf<String, Int>()

    private var navHistory = BottomNavHistory().apply { push(R.id.home_navigation) }

    private var selectedNavController: NavController? = null

    fun onBottomNavChanged(listener: NavController.OnDestinationChangedListener) {
        selectedNavController?.addOnDestinationChangedListener(listener)
    }


    override fun init(
        fragmentManager: FragmentManager,
        containerId: Int,
        bottomNavigationView: BottomNavigationView
    ) {
        this.fragmentManager = fragmentManager
        this.containerId = containerId
        this.bottomNavigationView = bottomNavigationView

        setupNavController()
        //bottomNavigationView.selectedItemId = R.id.home_navigation
    }

    override fun setupNavController() {
        navGraphStartDestinations.clear()
        graphIdToTagMap.clear()

        // create a NavHostFragment for each NavGraph ID
        createNavHostFragmentsForGraphs()

        // When a navigation item is selected
        bottomNavigationView.setupItemClickListener()
        clickIdItem(bottomNavigationView.selectedItemId)
    }

    private fun getTitleBottomNav(id: Int): String {
        return when (id) {
            R.id.home_navigation -> {
                "home"
            }
            R.id.authorization_navigation -> {
                "autho"
            }
            R.id.services_navigation -> {
                "services"
            }
            R.id.notification_navigation -> {
                "notification"
            }
            R.id.profile_person_navigation -> {
                "autho"
            }
            else -> "$id"
        }
    }

    private fun createNavHostFragmentsForGraphs() {
        // create a NavHostFragment for each NavGraph ID
        navGraphIds.forEachIndexed { index, navGraphId ->

            val fragmentTag = getFragmentTag(index.toString())

            var navHostFragment = obtainNavHostFragment(fragmentTag, navGraphId)
            if (navGraphId == R.navigation.profile_person) // TODO костыль потом исправить
                navHostFragment = obtainNavHostFragment(fragmentTag + isAuthorized, navGraphId)

            // Obtain its id
            val graphId = navHostFragment.navController.graph.id

            navGraphStartDestinations[getTitleBottomNav(graphId)] =
                navHostFragment.navController.graph.startDestination

            // Save to the map
            graphIdToTagMap[getTitleBottomNav(graphId)] = fragmentTag

            // Attach or detach nav host fragment depending on whether it's the selected item.
            if (bottomNavigationView.selectedItemId == graphId) {
                // Update nav controller with the selected graph
                selectedNavController = navHostFragment.navController
                showNavHostFragment(navHostFragment, false)
            } else {
                showNavHostFragment(navHostFragment, false)
            }
        }
    }


    private fun clickIdItem(id: Int) {
        navHistory.push(id)
        val newlySelectedItemTag = graphIdToTagMap[getTitleBottomNav(id)]
        val selectedFragment = fragmentManager.findFragmentByTag(newlySelectedItemTag)
                as NavHostFragment

        fragmentManager.beginTransaction()
            .show(selectedFragment)
            .setMaxLifecycle(selectedFragment, Lifecycle.State.RESUMED)
            .setPrimaryNavigationFragment(selectedFragment)
            .apply {
                // Detach all other Fragments
                graphIdToTagMap.forEach { (_, fragmentTag) ->
                    if (fragmentTag != newlySelectedItemTag) {
                        val fragment = fragmentManager.findFragmentByTag(fragmentTag)!!
                        hide(fragment)
                        setMaxLifecycle(fragment, Lifecycle.State.STARTED)
                    }
                }
            }.commit()

        selectedNavController = selectedFragment.navController
    }

    private fun clickMenuItem(item: MenuItem): Boolean {
        // do nothing on tab re-selection
        //  if (item.isChecked)
        //      return@setOnMenuItemClickListener true
        if (!fragmentManager.isStateSaved) {
            item.isChecked = true
            clickIdItem(item.itemId)
        }
        return true
    }


    private fun BottomNavigationView.setupItemClickListener() {
        menu.forEach { item ->
            item.setOnMenuItemClickListener {
                clickMenuItem(item)
            }
        }
    }

    // select particular bottom navigation item
    private fun selectItem(itemId: Int) {
        bottomNavigationView.menu.findItem(itemId)
            ?.let {
                bottomNavigationView.menu.performIdentifierAction(itemId, 0)
            }
    }

    // controls the back press mechanism
    override fun onBackPressed(): Boolean {
        return if (navHistory.isNotEmpty) {
            selectedNavController?.let {
                val expectedGraph =
                    navGraphStartDestinations[getTitleBottomNav(bottomNavigationView.selectedItemId)]
                val currentGraph = it.currentDestination?.id
                if (it.currentDestination == null || currentGraph == expectedGraph) {
                    if (isFirstTab())
                        return false

                    navHistory.pop(bottomNavigationView.selectedItemId)
                    selectItem(navHistory.current())
                    return true
                } else if (navHistory.size >= 1) {


                    return false
                }

                return false // super.onBackPressed() will be called, which will pop the fragment itself
            } ?: false
        } else false
    }

    // to save the tab history during any configuration change
    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelable(KEY_NAV_HISTORY, navHistory)
    }

    // to restore the tab history after any configuration change
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            navHistory = it.getParcelable<BottomNavHistory>(KEY_NAV_HISTORY) as BottomNavHistory
        }
    }

    // gets the NavHostFragment for particular index
    fun getNavHostFragment(index: Int): NavHostFragment? {
        return fragmentManager.findFragmentByTag(
            getFragmentTag(index.toString())
        ) as NavHostFragment?
    }

    private fun isFirstTab(): Boolean {
        return bottomNavigationView.selectedItemId == R.id.home_navigation
    }

    private fun showNavHostFragment(
        navHostFragment: NavHostFragment,
        show: Boolean
    ) {
        fragmentManager.beginTransaction()
            .apply {
                if (show) {
                    show(navHostFragment)
                    setMaxLifecycle(navHostFragment, Lifecycle.State.RESUMED)
                } else {
                    hide(navHostFragment)
                    setMaxLifecycle(navHostFragment, Lifecycle.State.STARTED)
                }
            }.commitNow()
    }

    private fun obtainNavHostFragment(fragmentTag: String, navGraphId: Int): NavHostFragment {
        // If the Nav Host fragment exists, return it
        val existingFragment = fragmentManager.findFragmentByTag(fragmentTag) as NavHostFragment?
        existingFragment?.let { return it }

        // Otherwise, create it and return it.
        val navHostFragment = NavHostFragment.create(navGraphId)
        fragmentManager
            .beginTransaction()
            .add(containerId, navHostFragment, fragmentTag)
            .commitNow()
        return navHostFragment
    }

    private fun FragmentManager.isOnBackStack(backStackName: String): Boolean {
        val backStackCount = backStackEntryCount
        for (index in 0 until backStackCount) {
            if (getBackStackEntryAt(index).name == backStackName) {
                return true
            }
        }
        return false
    }

    private fun getFragmentTag(index: String) = "BottomNavManager#$index"
}