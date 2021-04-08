package com.aldredo.Intelligence

import android.app.Application
import com.aldredo.Intelligence.di.AppComponent
import com.aldredo.core.base.navigation.AppWithProvide
import com.aldredo.core.base.navigation.ProvideAppComponent

class App : Application(), AppWithProvide {

    override fun getFacade(): ProvideAppComponent {
        return appComponent ?: AppComponent.create(this).apply {
            appComponent = this
        }
    }

    companion object {
        var appComponent: ProvideAppComponent? = null
    }
}