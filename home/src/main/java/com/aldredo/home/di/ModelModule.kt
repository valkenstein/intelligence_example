package com.aldredo.home.di

import androidx.lifecycle.ViewModelProviders
import com.aldredo.home.data.Repository
import com.aldredo.home.presentation.activity.HomeFragment
import com.aldredo.home.presentation.mvvm.HomeViewModel
import com.aldredo.core.base.mvvm.ModelFactory
import com.aldredo.home.navigation.Router
import dagger.Module
import dagger.Provides

@Module
class ModelModule {
    @ActivityScope
    @Provides
    fun provideModel(
        activity: HomeFragment,
        repository: Repository,
        router: Router
    ): HomeViewModel {
        val model = HomeViewModel(repository, router)
        return ViewModelProviders.of(activity, ModelFactory(model)).get(model::class.java)
    }
}