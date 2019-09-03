package io.keyu.dagger

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity
import io.keyu.dagger.ui.auth.AuthActivity
import io.keyu.dagger.ui.auth.AuthResource
import javax.inject.Inject

// Auth state are automatically handled in here.
abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject lateinit var sessionManager: SessionManager

    private fun subscribeObservers() {
        sessionManager.cachedUser.observe(this, Observer {
            when (it.status) {
                AuthResource.AuthStatus.LOADING -> {

                }
                AuthResource.AuthStatus.AUTHENTICATED -> {

                }
                AuthResource.AuthStatus.ERROR -> {

                }
                AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                    launchLoginScreen()
                }
            }
        })
    }

    private fun launchLoginScreen() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}