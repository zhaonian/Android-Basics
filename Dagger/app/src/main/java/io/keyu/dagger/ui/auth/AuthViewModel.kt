package io.keyu.dagger.ui.auth

import androidx.lifecycle.*
import io.keyu.dagger.SessionManager
import io.keyu.dagger.model.User
import io.keyu.dagger.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : ViewModel() {

    val authUserLiveData: LiveData<AuthResource<User>> = sessionManager.cachedUser

    fun authenticateWithId(userId: Int) {
        sessionManager.authenticateWithId(queryUserId(userId))
    }

    private fun queryUserId(userId: Int): LiveData<AuthResource<User>> =
        LiveDataReactiveStreams.fromPublisher(authApi.getUser(userId)
            .onErrorReturn {
                User(id = -1, email = "", username = "", website = "")
            }.map {
                if (it.id == -1) {
                    AuthResource.error("Could not authenticate", null)
                } else {
                    AuthResource.authenticated(it)
                }
            }
            .subscribeOn(Schedulers.io())
        ) as LiveData<AuthResource<User>> // the cast is needed here.
}
