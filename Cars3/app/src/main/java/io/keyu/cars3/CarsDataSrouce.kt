package io.keyu.cars3

class CarsDataSrouce() {
    var carsList: ArrayList<Car>? = null

    init {
        carsList = arrayListOf(
            Car("Lightning McQueen", "a race car", R.drawable.lightning_mcqueen, true),
            Car("Mater", "a toll truck", R.drawable.mater, false),
            Car("Miss Fritter", "a school bus", R.drawable.miss_fritter, false),
            Car("Sally Carrera", "a porsche", R.drawable.sally_carrera, true),
            Car("Cruz Ramirez", "an exotic car", R.drawable.cruz_ramirez, false),
            Car("Holley Shiftwell", "a shift car", R.drawable.holley_shiftwell, true),
            Car("Jackson Storm", "a professional race car", R.drawable.jackson_storm, true)
        )
    }
}