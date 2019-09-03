package io.keyu.dagger.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.keyu.dagger.di.auth.AuthModule
import io.keyu.dagger.di.auth.AuthViewModelsModule
import io.keyu.dagger.ui.auth.AuthActivity
import io.keyu.dagger.ui.main.MainActivity

@Module
abstract class ActivitiesBuilderModule {

    // let dagger know [AuthActivity] is a potential client.
    @ContributesAndroidInjector(
        modules = [AuthViewModelsModule::class, AuthModule::class] // only scope this to AuthActivity
    )
    abstract fun contributeAuthActivity(): AuthActivity // a sub-compoennt

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity
}
