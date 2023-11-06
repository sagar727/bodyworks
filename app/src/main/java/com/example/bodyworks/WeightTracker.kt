package com.example.bodyworks

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.bodyworks.databinding.ActivityWeightTrackerBinding
import com.example.bodyworks.viewModel.BodyWorksViewModel
import com.github.chartcore.common.ChartTypes
import com.github.chartcore.common.Position
import com.github.chartcore.common.TextAlign
import com.github.chartcore.data.chart.ChartCoreModel
import com.github.chartcore.data.chart.ChartData
import com.github.chartcore.data.dataset.ChartNumberDataset
import com.github.chartcore.data.option.ChartOptions
import com.github.chartcore.data.option.elements.Elements
import com.github.chartcore.data.option.elements.Line
import com.github.chartcore.data.option.plugin.Plugin
import com.github.chartcore.data.option.plugin.Title
import com.github.chartcore.data.option.plugin.Tooltip
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class WeightTracker : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    lateinit var binding: ActivityWeightTrackerBinding
    lateinit var bodyworksVM: BodyWorksViewModel
    val calendar: Calendar = Calendar.getInstance()
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.CANADA)
    val kgArray = doubleArrayOf().toMutableList()
    val lbArray = doubleArrayOf().toMutableList()
    val dateArray = arrayListOf<String>().toMutableList()

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_tracker)

        binding = ActivityWeightTrackerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bodyworksVM = ViewModelProvider(this).get(BodyWorksViewModel::class.java)

        val toolbar = binding.materialToolbar2
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val date = dateFormatter.format(calendar.timeInMillis)
        binding.dateET.setText(date)

        bodyworksVM.getWeightTrackerData(this)

        observeKgArray()
        observeDtArray()
        observeLbArray()

        val coreData = ChartData()
            .addDataset(
                ChartNumberDataset()
                    .data(kgArray)
                    .label("weight")
                    .offset(10)
                    .borderColor("#E11B0C")
            )
            .labels(dateArray)

        val chartOptions = ChartOptions()
            .plugin(
                Plugin()
                    .subtitle(
                        Title()
                            .display(false)
                            .text("Subtitle example")
                            .position(Position.BOTTOM)
                    ).title(
                        Title()
                            .display(true)
                            .text("Weight Tracker Chart")
                            .position(Position.TOP)
                            .align(TextAlign.CENTER)
                            .color("red")
                    )
                    .tooltip(
                        Tooltip(false)
                    )
            )
            .elements(
                Elements()
                    .line(
                        Line()
                            .tension(0.5f)
                    )
            )


        val chartModel = ChartCoreModel()
            .type(ChartTypes.LINE)
            .data(coreData)
            .options(chartOptions)

        binding.chartCore.draw(chartModel)

        binding.saveBtn.setOnClickListener {
            val dt = binding.dateET.text.toString()
            val wt = binding.wtET.text.toString().trim()
            if((wt.length == 4 && !wt.contains(".",false)) || wt == ""){
                Toast.makeText(this,"Please enter valid weight!!",Toast.LENGTH_SHORT).show()
            }else{
                bodyworksVM.addWeightTrackerData(this, wt.toDouble(), dt)
                binding.wtET.text?.clear()
            }
        }

        binding.datePickerBtn.setOnClickListener {
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            val datePicker = DatePickerDialog(this, this, year, month, day)
            datePicker.datePicker.maxDate = calendar.timeInMillis
            datePicker.show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
        val date = dateFormatter.format(calendar.timeInMillis)
        binding.dateET.setText(date)
    }

    private fun observeKgArray(){
        bodyworksVM.kgArray.observe(this){kiloArray ->
            kgArray.addAll(kiloArray)
        }
    }

    private fun observeDtArray(){
        bodyworksVM.dtArray.observe(this){dtArray ->
            dateArray.addAll(dtArray)
        }
    }

    private fun observeLbArray(){
        bodyworksVM.lbArray.observe(this){poundArray ->
            lbArray.addAll(poundArray)
        }
    }
}