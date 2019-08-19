package io.keyu.dagger.di.auth

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.keyu.dagger.di.ViewModelKey
import io.keyu.dagger.ui.auth.AuthViewModel

/**
 * Dependencies for Auth-related ViewModels.
 */
@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}
