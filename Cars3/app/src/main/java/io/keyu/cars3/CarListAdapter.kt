package io.keyu.cars3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.car_row.view.*

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
        carView = layoutInflater.inflate(R.layout.car_row, null)
        carView.carImage.setImageResource(car.image)
        carView.carName.text = car.name
        carView.carDescription.text = car.description
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
}