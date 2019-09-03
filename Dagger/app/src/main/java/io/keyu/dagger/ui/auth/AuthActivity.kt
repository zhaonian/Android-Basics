package io.keyu.dagger.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import io.keyu.dagger.R
import io.keyu.dagger.ui.main.MainActivity
import io.keyu.dagger.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    @Inject lateinit var providerFactory: ViewModelProviderFactory
    @Inject lateinit var logo: Drawable
    @Inject lateinit var requestManager: RequestManager

    private var authViewModel: AuthViewModel? = null

    private var userId: EditText? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        userId = findViewById(R.id.user_id_input)
        progressBar = findViewById(R.id.progress_bar)

        findViewById<Button>(R.id.login_button).setOnClickListener(this)

        authViewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)

        setLogo()

        subscribeObservers()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login_button -> attemptLogin()
        }
    }

    private fun setLogo() {
        requestManager.load(logo).into(findViewById(R.id.login_logo))
    }

    private fun attemptLogin() {
        if (TextUtils.isEmpty(userId?.text.toString())) {
            return
        }
        authViewModel?.authenticateWithId(userId?.text.toString().toInt())
    }

    private fun subscribeObservers() {
        authViewModel?.authUserLiveData?.observe(this, Observer {
            it?.let {
                Log.d(TAG, it.status.name)

                when (it.status) {
                    AuthResource.AuthStatus.LOADING -> {
                        showProgressBar(true)
                    }

                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                        Log.d(TAG, "onChanged: LOGIN SUCCESS: " + it.data?.email)
                        onLoginSuccess()
                    }

                    AuthResource.AuthStatus.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(
                            this@AuthActivity,
                            it.message + "\nDid you enter a number between 1 and 10?",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        showProgressBar(false)
                    }
                }
            }
        })
    }

    private fun showProgressBar(isVisible: Boolean) {
        progressBar?.let {
            it.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }

    private fun onLoginSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    companion object {
        private const val TAG = "AuthActivity"
    }
}
