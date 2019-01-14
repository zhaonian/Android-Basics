package io.keyu.cars3

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bio.*

class BioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bio)

        var receivedData = intent.extras
        var carName = receivedData.getString(CAR_NAME)
        var carDescription = receivedData.getString(CAR_DESCRIPTION)
        var carImage = receivedData.getInt(CAR_IMAGE)

        bioCarImage.setImageResource(carImage)
        bioCarText.text = "$carName - $carDescription"

    }

    companion object {
        const val CAR_NAME = "car_name"
        const val CAR_DESCRIPTION = "car_description"
        const val CAR_IMAGE = "car_image"
    }
}