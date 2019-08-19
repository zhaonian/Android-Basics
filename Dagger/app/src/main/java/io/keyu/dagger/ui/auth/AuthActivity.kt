package io.keyu.dagger.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import io.keyu.dagger.R
import io.keyu.dagger.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    @Inject lateinit var providerFactory: ViewModelProviderFactory
    @Inject lateinit var logo: Drawable
    @Inject lateinit var requestManager: RequestManager

    private var authViewModel: AuthViewModel? = null

    private var userId: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        userId = findViewById(R.id.user_id_input)

        findViewById<Button>(R.id.login_button).setOnClickListener(this)

        authViewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)

        setLogo()

        subscribeOberservers()
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

    private fun subscribeOberservers() {
        authViewModel?.authUserLiveData?.observe(this, Observer {
            it?.let {
                Log.d("haha", it.email)
            }
        })
    }
}
