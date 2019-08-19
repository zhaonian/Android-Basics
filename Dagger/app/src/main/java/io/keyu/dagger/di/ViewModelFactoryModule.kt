package io.keyu.dagger.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import io.keyu.dagger.viewmodel.ViewModelProviderFactory

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelProviderFactory
    ): ViewModelProvider.Factory

//    /**
//     * This plays exactly the same role as the above function.
//     * The above one is more efficient because we just need to return [factory] here.
//     */
//    @Provides
//    fun bindViewModelFactory(
//        factory: ViewModelProviderFactory
//    ): ViewModelProvider.Factory {
//        return factory
//    }
}