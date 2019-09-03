package io.keyu.dagger.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import io.keyu.dagger.BaseActivity
import io.keyu.dagger.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.logout -> {
                sessionManager.logout()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
