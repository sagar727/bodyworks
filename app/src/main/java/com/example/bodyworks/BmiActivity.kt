package com.example.bodyworks

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.bodyworks.databinding.ActivityBmiBinding
import com.example.bodyworks.databinding.BmiInfoDialogBinding
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

        val toolbar = binding.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        bmiVM.getBMI(this)

        radioChecked()

        binding.unitRadioGroup.setOnCheckedChangeListener { _, _ ->
            radioChecked()
        }

        val inches = bmiVM.generateDropDownNumbrs(0,13)
        val feets = bmiVM.generateDropDownNumbrs(1,8)
        val cms = bmiVM.generateDropDownNumbrs(30,240)

        val inchAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,inches)
        val feetAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,feets)
        val cmAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,cms)
        binding.inchDropDown.setAdapter(inchAdapter)
        binding.feetDropDown.setAdapter(feetAdapter)
        binding.cmDropDown.setAdapter(cmAdapter)

        binding.calcBtn.setOnClickListener {

            val ht = binding.cmDropDown.text.toString().trim()
            val wt = binding.wtET.text.toString().trim()
            val ft = binding.feetDropDown.text.toString().trim()
            val inch = binding.inchDropDown.text.toString().trim()

            if (binding.metricRadioBtn.isChecked) {
                if((wt == "" || ht == "" || ht == "Centimeters") || (wt.length == 4 && !wt.contains(".",false))){
                    Toast.makeText(this,"Please add all details!!",Toast.LENGTH_LONG).show()
                }else{
                    bmiVM.calculateBmiInMetric(this,wt.toDouble(),ht.toDouble())
                    cleanUp()
                }
            } else if (binding.imperialRadioBtn.isChecked) {
                if((wt == "" || ft == "" || ft == "Feet" || inch == "" || inch == "Inch") || (wt.length == 4 && !wt.contains(".",false))){
                    Toast.makeText(this,"Please add all details!!",Toast.LENGTH_LONG).show()
                }else{
                    bmiVM.calculateBmiInImperial(this,wt.toDouble(),ft.toInt(),inch.toInt())
                    cleanUp()
                }
            }
        }

        binding.helpBtn.setOnClickListener {
            val dialogMainBinding = BmiInfoDialogBinding.inflate(layoutInflater)
            val dialog = Dialog(this,R.style.CustomAlertDialog)
            dialog.setContentView(dialogMainBinding.root)
            dialogMainBinding.dialogOkBtn.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        observeBmiData()
    }

    private fun cleanUp(){
        binding.wtET.text?.clear()
    }

    private fun radioChecked() {
        if (binding.metricRadioBtn.isChecked) {
            binding.imperialLL.visibility = View.GONE
            binding.textField2.visibility = View.VISIBLE
            binding.cmDropDown.visibility = View.VISIBLE
            binding.textField1.hint = "Kilograms"
        } else if (binding.imperialRadioBtn.isChecked) {
            binding.cmDropDown.visibility = View.GONE
            binding.textField2.visibility = View.GONE
            binding.imperialLL.visibility = View.VISIBLE
            binding.textField1.hint = "Lbs"
        }
    }

    private fun observeBmiData(){
        bmiVM.bmi.observe(this){bmiData ->
            binding.bmiTV.text = bmiData
            val bmiInDouble = bmiData.toDouble()
            if(bmiInDouble < 18.5){
                binding.bmiTV.setTextColor(getColor(R.color.max_blue))
            }else if(bmiInDouble in 18.5..24.99){
                binding.bmiTV.setTextColor(getColor(R.color.green))
            }else if(bmiInDouble in 25.0 .. 29.99){
                binding.bmiTV.setTextColor(getColor(R.color.yellow))
            }else if(bmiInDouble in 30.0 .. 39.99){
                binding.bmiTV.setTextColor(getColor(R.color.red))
            }else if(bmiInDouble >= 40.0){
                binding.bmiTV.setTextColor(getColor(R.color.purple))
            }else{
                binding.bmiTV.setTextColor(getColor(R.color.black))
            }
        }
    }
}