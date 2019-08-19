package io.keyu.dagger.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.keyu.dagger.ui.auth.AuthActivity

@Module
abstract class ActivitiesBuilderModule {

    // let dagger know [AuthActivity] is a potential client.
    @ContributesAndroidInjector
    abstract fun contributeAuthActivity(): AuthActivity
}
