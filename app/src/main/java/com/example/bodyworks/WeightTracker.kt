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
    var kiloArray = arrayListOf<Double>()
    var poundArray = arrayListOf<Double>()
    var dateArray = arrayListOf<String>()

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

        loadChart()

        binding.saveBtn.setOnClickListener {
            val dt = binding.dateET.text.toString()
            val wt = binding.wtET.text.toString().trim()
            if((wt.length == 4 && !wt.contains(".",false)) || wt == ""){
                Toast.makeText(this,"Please enter valid weight!!",Toast.LENGTH_SHORT).show()
            }else{
                bodyworksVM.addWeightTrackerData(this, wt.toDouble(), dt)
                binding.wtET.text?.clear()
                binding.chartCore.reload()
                kiloArray.clear()
                poundArray.clear()
                dateArray.clear()
                loadChart()
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

    private fun loadChart() {
        val data = bodyworksVM.getWeightTrackerData(this)

        var i = 0
        val size = data.size
        while (i < size) {
            kiloArray.add(data[i].kg)
            poundArray.add(data[i].lb)
            dateArray.add(data[i].dt)
            i++
        }

        val coreData = ChartData()
            .addDataset(
                ChartNumberDataset()
                    .data(kiloArray)
                    .label(getString(R.string.weight))
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
                            .text(getString(R.string.weight_tracker_chart))
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
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
        val date = dateFormatter.format(calendar.timeInMillis)
        binding.dateET.setText(date)
    }
}