package io.keyu.dagger.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.keyu.dagger.BaseApplication
import javax.inject.Singleton

/**
 * Think this as a service to the [BaseApplication].
 *
 * Component as the a service, fragment/activity/application are the clients who use the service.
 * This component owns the @Singleton scope, if this component died, all the @Singleton dependencies
 * will die with it.
 *
 * @Single is just a label, and we can create a random one that plays exactly the same role.
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivitiesBuilderModule::class,
        AppModule::class,
        ViewModelFactoryModule::class
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
