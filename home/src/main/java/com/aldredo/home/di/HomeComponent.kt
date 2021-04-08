package com.aldredo.home.di

import com.aldredo.core.base.navigation.AppWithProvide
import com.aldredo.core.base.navigation.ProvideAppComponent
import com.aldredo.home.presentation.activity.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@ActivityScope
@Component(
    dependencies = [ProvideAppComponent::class],
    modules = [ModelModule::class, ServiceApiModule::class]
)
interface HomeComponent {
    fun inject(app: HomeFragment)

    companion object {
        fun create(homeFragment: HomeFragment) {
            val app = (homeFragment.requireActivity().application as AppWithProvide).getFacade()
            DaggerHomeComponent
                .builder()
                .appComponent(app)
                .setActivity(homeFragment)
                .build()
                .inject(homeFragment)
        }
    }

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setActivity(context: HomeFragment): Builder

        fun appComponent(provideAppComponent: ProvideAppComponent): Builder

        fun build(): HomeComponent
    }
}

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope