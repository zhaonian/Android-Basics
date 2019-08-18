package io.keyu.dagger.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.keyu.dagger.BaseApplication

/**
 * Think this as a service to the [BaseApplication].
 *
 * Component as the a service, fragment/activity/application are the clients who use the service.
 */
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivitiesBuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
