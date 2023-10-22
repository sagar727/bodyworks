package com.example.bodyworks.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class BodyWorksViewModel: ViewModel() {
    var bmi = MutableLiveData<String>()

    init {
        bmi.value = "0"
    }

    fun calculateBmiInMetric(wt: Double, ht: Double) {
        val cmToMeter = ht / 100
        val result = ((wt / (cmToMeter * cmToMeter)) * 100.0).roundToInt() / 100.0
        bmi.value = result.toString()
    }

    fun calculateBmiInImperial(wt: Double, ft: Int, inch: Int){
        val ftToInch = ft * 12.5
        val totalInch = ftToInch + inch
        val result = ((703 * wt / (totalInch * totalInch)) * 100.0).roundToInt() / 100.0
        bmi.value = result.toString()
    }
}

