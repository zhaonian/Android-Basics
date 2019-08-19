package io.keyu.dagger.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import io.keyu.dagger.R
import io.keyu.dagger.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    @Inject lateinit var providerFactory: ViewModelProviderFactory
    @Inject lateinit var logo: Drawable
    @Inject lateinit var requestManager: RequestManager

    private var authViewModel: AuthViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        authViewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)

        setLogo()
    }

    private fun setLogo() {
        requestManager.load(logo).into(findViewById(R.id.login_logo))
    }
}
