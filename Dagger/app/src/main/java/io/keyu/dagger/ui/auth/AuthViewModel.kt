package io.keyu.dagger.ui.auth

import androidx.lifecycle.*
import io.keyu.dagger.model.User
import io.keyu.dagger.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    private val authUser = MediatorLiveData<User>()
    val authUserLiveData: LiveData<User> = authUser

    fun authenticateWithId(userId: Int) {
        val source = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId).subscribeOn(Schedulers.io())
        )

        authUser.addSource(source) {
            authUser.value = it
            authUser.removeSource(source)
        }
    }
}
