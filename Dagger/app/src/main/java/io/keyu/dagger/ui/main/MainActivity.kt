package io.keyu.dagger.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import io.keyu.dagger.BaseActivity
import io.keyu.dagger.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
    }
}
