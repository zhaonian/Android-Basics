package io.keyu.cars3

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.car_row.view.carName
import kotlinx.android.synthetic.main.car_row.view.carDescription
import kotlinx.android.synthetic.main.car_row.view.carImage

class CarListAdapter : BaseAdapter {

    private var carsDataSource: CarsDataSrouce? = null
    private var context: Context? = null

    constructor(context: Context) {
        carsDataSource = CarsDataSrouce()
        this.context = context
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var car: Car = carsDataSource?.carsList?.get(p0) ?:
            Car("N/A", "N/A", R.drawable.placeholder_car, false)
        var carView: View
        var layoutInflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                as LayoutInflater

        carView = if (car.canFly) {
            layoutInflater.inflate(R.layout.airborne_car_row, null)
        } else {
            layoutInflater.inflate(R.layout.car_row, null)
        }
        carView.carImage.setImageResource(car.image)
        carView.carName.text = car.name
        carView.carDescription.text = car.description

        carView.setOnClickListener {
            val carBioIntent = Intent(context, BioActivity::class.java)
            carBioIntent.putExtra(BioActivity.CAR_NAME, car.name)
            carBioIntent.putExtra(BioActivity.CAR_DESCRIPTION, car.description)
            carBioIntent.putExtra(BioActivity.CAR_IMAGE, car.image)
            startActivity(context as Context, carBioIntent, null)
        }

        carView.setOnLongClickListener {
            this.showDialog(p0)
            return@setOnLongClickListener true
        }

        return carView
    }

    override fun getItem(p0: Int): Any {
        return carsDataSource?.carsList?.get(p0) ?:
            Car("N/A", "N/A", R.drawable.placeholder_car, false)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return carsDataSource?.carsList?.size ?: 0
    }

    private fun showDialog(index: Int) {
        var alertDialog: AlertDialog =
                AlertDialog.Builder(context).create()
        alertDialog.setTitle("Hello")
        alertDialog.setMessage("It's me")
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Delete") {
                _: DialogInterface, _: Int ->
            carsDataSource?.carsList?.removeAt(index)
            this@CarListAdapter.notifyDataSetChanged()
        }
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Duplicate") {
                _: DialogInterface, _: Int ->
            carsDataSource?.carsList?.add(index, carsDataSource?.carsList?.get(index) ?:
                Car("N/A", "N/A", R.drawable.placeholder_car, false))
            this@CarListAdapter.notifyDataSetChanged()
        }
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel") {
                _: DialogInterface, _: Int ->
            alertDialog.dismiss()
        }
        alertDialog.setCancelable(true)
        alertDialog.show()
    }
}