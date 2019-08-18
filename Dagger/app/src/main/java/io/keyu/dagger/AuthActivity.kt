package io.keyu.dagger

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    @Inject lateinit var logo: Drawable
    @Inject lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setLogo()
    }

    private fun setLogo() {
        requestManager.load(logo).into(findViewById(R.id.login_logo))
    }
}
