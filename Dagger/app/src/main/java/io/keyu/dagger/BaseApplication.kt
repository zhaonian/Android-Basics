package io.keyu.dagger

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.keyu.dagger.di.DaggerAppComponent

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
