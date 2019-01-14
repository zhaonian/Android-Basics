package io.keyu.cars3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.carsListView

class MainActivity : AppCompatActivity() {

    private var carListAdapter: CarListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carListAdapter = CarListAdapter(this@MainActivity)
        carsListView.adapter = carListAdapter
    }
}
