package io.keyu.dagger

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.keyu.dagger.model.User
import io.keyu.dagger.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private val _cachedUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    val cachedUser: LiveData<AuthResource<User>> = _cachedUser

    fun authenticateWithId(source: LiveData<AuthResource<User>>) {
        _cachedUser.value = AuthResource.loading(null)
        _cachedUser.addSource(source) {
            _cachedUser.value = it
            _cachedUser.removeSource(source)
        }
    }

    fun logout() {
        _cachedUser.value = AuthResource.logout()
    }
}