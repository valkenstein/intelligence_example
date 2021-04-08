package com.aldredo.Intelligence.di

import android.app.Application
import android.content.Context
import com.aldredo.core.base.di.HttpComponent
import com.aldredo.core.base.navigation.ProvideAppComponent
import com.aldredo.core.base.scoupe.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [NavigationModule::class, MediatorModule::class],
    dependencies = [HttpComponent::class]
)
interface AppComponent : ProvideAppComponent{
    companion object {
        fun create(app: Application): AppComponent =
            DaggerAppComponent
                .builder()
                .httpComponent(HttpComponent.create())
                .application(app)
                .build()
    }

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(context: Context): Builder

        fun httpComponent(httpComponent: HttpComponent): Builder

        fun build(): AppComponent
    }
}
