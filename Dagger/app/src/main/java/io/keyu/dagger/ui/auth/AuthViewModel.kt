package io.keyu.dagger.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import io.keyu.dagger.network.auth.AuthApi
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {
    init {
        authApi.getUser(1).toObservable().subscribeOn(Schedulers.io()).subscribeBy(
            onNext = {
                Log.d("hehe", it.username)
            },
            onError = {
                Log.e("hahaha", it.message)
            },
            onComplete = {}
        )
    }
}
