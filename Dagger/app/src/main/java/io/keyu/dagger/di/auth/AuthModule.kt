package io.keyu.dagger.di.auth

import dagger.Module
import dagger.Provides
import io.keyu.dagger.network.auth.AuthApi
import retrofit2.Retrofit

@Module
class AuthModule {

    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}