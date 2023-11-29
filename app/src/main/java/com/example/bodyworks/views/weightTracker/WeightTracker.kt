package com.example.bodyworks.views.weightTracker

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.bodyworks.R
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
import java.util.Date
import java.util.Locale

class WeightTracker : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityWeightTrackerBinding
    private lateinit var bodyworksVM: BodyWorksViewModel
    private val calendar: Calendar = Calendar.getInstance()
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.CANADA)
    private var kiloArray = arrayListOf<Double>()
    private var poundArray = arrayListOf<Double>()
    private var dateArray = arrayListOf<String>()
    private var isMetric: Boolean = true
    private lateinit var coreData: ChartData
    private var currDate: Long = 0
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_tracker)

        binding = ActivityWeightTrackerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bodyworksVM = ViewModelProvider(this).get(BodyWorksViewModel::class.java)

        sharedPreferences = this.let { PreferenceManager.getDefaultSharedPreferences(it) }!!
        isMetric= sharedPreferences.getBoolean("isMetric",  false)

        if(!isMetric){
            binding.textField2.hint = "Kilogram"
        }else{
            binding.textField2.hint = "Lb"
        }

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
            val wt = binding.wtET.text.toString().trim()
            if ((wt.length == 4 && !wt.contains(".", false)) || wt == "") {
                Toast.makeText(this, "Please enter valid weight!!", Toast.LENGTH_SHORT).show()
            } else {
                bodyworksVM.addWeightTrackerData(this, wt.toDouble(), currDate, isMetric)
                binding.wtET.text?.clear()
                binding.chartCore.reload()
                kiloArray.clear()
                poundArray.clear()
                dateArray.clear()
                loadChart()
            }
        }

        binding.datePickerBtn.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            val datePicker = DatePickerDialog(this, this, year, month, day)
            datePicker.datePicker.maxDate = calendar.timeInMillis
            datePicker.show()
        }
    }

    //chart for weight tracker
    private fun loadChart() {
        val data = bodyworksVM.getWeightTrackerData(this)
        val i = 0
        var j = data.size

        while (i < j) {
            kiloArray.add(data[j-1].kg)
            poundArray.add(data[j-1].lb)
            val dateStr = dateFormatter.format(Date(data[j-1].dt))
            dateArray.add(dateStr)
            j--
        }

        if (isMetric) {
            coreData = ChartData()
                .addDataset(
                    ChartNumberDataset()
                        .data(kiloArray)
                        .label(getString(R.string.weight))
                        .offset(10)
                        .borderColor("#E11B0C")
                )
                .labels(dateArray)
        } else {
            coreData = ChartData()
                .addDataset(
                    ChartNumberDataset()
                        .data(poundArray)
                        .label(getString(R.string.weight))
                        .offset(10)
                        .borderColor("#E11B0C")
                )
                .labels(dateArray)
        }

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
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        currDate = calendar.timeInMillis
        val date = dateFormatter.format(calendar.timeInMillis)
        binding.dateET.setText(date)
    }
}