package com.example.bodyworks

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.example.bodyworks.databinding.ActivityCalorieBinding
import com.example.bodyworks.model.CalorieTracker
import com.example.bodyworks.viewModel.CalorieViewModel
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalorieTrackerActivity : AppCompatActivity() {

    private lateinit var viewModel: CalorieViewModel
    private lateinit var binding: ActivityCalorieBinding

    val calendar: Calendar = Calendar.getInstance()
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.CANADA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalorieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, AndroidViewModelFactory(application))[CalorieViewModel::class.java]

        val spinnerFoodItems: Spinner = binding.spinnerFoodItems
        val buttonAddItem: Button = binding.buttonAddItem
        val textViewTotalCalories: TextView = binding.textViewTotalCalories

        val foodItems = getFoodItems()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, foodItems.map { it.date })
        spinnerFoodItems.adapter = adapter

        val date = dateFormatter.format(calendar.timeInMillis)

        updateChart()
        
        buttonAddItem.setOnClickListener {
            val selectedItem = foodItems[spinnerFoodItems.selectedItemPosition]
            val data = viewModel.getCurrentCalorie(this, date)
            if(data.size != 0){
                var calorie = data[0].calories
                calorie += selectedItem.calories
                viewModel.updateCalorie(this, CalorieTracker(date, calorie))
            } else{
                viewModel.insertCalorie(this, CalorieTracker(date, selectedItem.calories))
            }
            updateChart()
            binding.chartCore.reload()

        }

        viewModel.totalCalories.observe(this) { total ->
            textViewTotalCalories.text = "Total Calories: $total"
        }
    }

    private fun updateChart() {

        val calorieData = viewModel.getCalorieTrackerData(this)

        // Calculate the total calories and store it in the chart data
        val data = calorieData.map { it.calories }
        val labels = calorieData.map { it.date }

        val chartData = ChartData()
        chartData.addDataset(
            ChartNumberDataset()
                .data(data)
                .label("Calorie")
        )
        chartData.labels(labels)

        val chartOptions = ChartOptions()
            .plugin(
                Plugin()
                    .title(
                        Title()
                            .display(true)
                            .text("Calorie Tracker")
                            .position(Position.TOP)
                            .align(TextAlign.CENTER)
                            .color("red")
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
            .data(chartData)
            .options(chartOptions)

        binding.chartCore.draw(chartModel)
    }


    private fun getFoodItems(): List<CalorieTracker> {
        return listOf(
            CalorieTracker("Apple", 95.0),
            CalorieTracker("Banana", 105.0),
            CalorieTracker("Orange", 62.0),
            CalorieTracker("Milk", 149.0),
            CalorieTracker("Egg", 72.0),
            CalorieTracker("Fish", 218.0),
            CalorieTracker("Chicken", 187.0),
            CalorieTracker("Lamb", 250.0),
            CalorieTracker("Rice", 121.0),
            CalorieTracker("Bread", 247.0),
            CalorieTracker("Potato", 93.0),
            CalorieTracker("Tofu", 145.0),
            CalorieTracker("Cheese", 403.0),
            CalorieTracker("Beef", 250.0),
        )
    }
}
