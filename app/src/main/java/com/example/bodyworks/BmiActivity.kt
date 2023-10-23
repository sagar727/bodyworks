package com.example.bodyworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.bodyworks.databinding.ActivityBmiBinding
import com.example.bodyworks.viewModel.BodyWorksViewModel

class BmiActivity : AppCompatActivity() {

    lateinit var binding: ActivityBmiBinding
    lateinit var bmiVM: BodyWorksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bmiVM = ViewModelProvider(this).get(BodyWorksViewModel::class.java)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        radioChecked()

        binding.unitRadioGroup.setOnCheckedChangeListener { _, _ ->
            radioChecked()
        }

        binding.calcBtn.setOnClickListener {
            val ht = binding.htET.text.toString().trim()
            val wt = binding.wtET.text.toString().trim()
            val ft = binding.ftET.text.toString().trim()
            val inch = binding.inchET.text.toString().trim()
            if (binding.metricRadioBtn.isChecked) {
                if(wt != "" && ht != ""){
                    bmiVM.calculateBmiInMetric(this,wt.toDouble(),ht.toDouble())
                }else{
                    Toast.makeText(this,"Please add all details!!",Toast.LENGTH_LONG).show()
                }
            } else if (binding.imperialRadioBtn.isChecked) {
                if(wt != "" && ft != "" && inch != ""){
                    bmiVM.calculateBmiInImperial(this,wt.toDouble(),ft.toInt(),inch.toInt())
                }else{
                    Toast.makeText(this,"Please add all details!!",Toast.LENGTH_LONG).show()
                }
            }
        }

        observeBmiData()
    }

    private fun radioChecked() {
        if (binding.metricRadioBtn.isChecked) {
            binding.imperialLL.isVisible = false
            binding.textField2.isVisible = true
            binding.htET.isVisible = true
            binding.textField1.hint = "Kilograms"
        } else if (binding.imperialRadioBtn.isChecked) {
            binding.htET.isVisible = false
            binding.textField2.isVisible = false
            binding.imperialLL.isVisible = true
            binding.textField1.hint = "Lbs"
        }
    }

    private fun observeBmiData(){
        bmiVM.bmi.observe(this){bmiData ->
            binding.bmiTV.text = bmiData
        }
    }
}