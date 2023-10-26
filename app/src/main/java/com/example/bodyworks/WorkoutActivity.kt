package com.example.bodyworks

import android.app.Dialog
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.bodyworks.database.DatabaseHelper
import com.example.bodyworks.databinding.ActivityWorkoutBinding
import com.example.bodyworks.model.WorkoutDataModel
import java.util.Locale

class WorkoutActivity : AppCompatActivity() {

    private var timeSelected : Int = 0
    private var timeCountdown : CountDownTimer? = null
    private var timeProgress = 0
    private var timeOffSet : Long = 0
    private var isStart = true
    private var categoryForDB: String="";
    private lateinit var dbHelper: DatabaseHelper

    lateinit var binding: ActivityWorkoutBinding
    val text = "Transversus abdominis, Rectus abdominis, Internal oblique, External oblique muscles"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar2
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Getting data from the intent
        val categoryTitle = intent.getStringExtra("categoryTitle")?.lowercase()?.trim()
        val imgSrc = intent.getStringExtra("imgSrc")
        val workoutName = intent.getStringExtra("workoutName")

        //Database Code
        var workOut: WorkoutDataModel = WorkoutDataModel("Empty", "Empty", "Empty", "Empty")
        val db = DatabaseHelper(applicationContext)
        val workOutData = categoryTitle?.let { db.displayAll(it) }
        workOutData?.forEach {
            if(it.name == workoutName)
                workOut = it
        }

        if(workOut.video != "Empty" && workOut.name != "Empty" && workOut.thumbnail != "Empty" && workOut.muscle != "Empty"){
            val tempArray = workOut.video.split("\\.".toRegex())
            val resourceId = resources.getIdentifier(tempArray[2], "raw", packageName)
            binding.videoView.setVideoURI(Uri.parse("android.resource://$packageName/$resourceId"))
            binding.videoView.requestFocus()
            binding.videoView.start()

            binding.videoView.setOnCompletionListener {
                binding.videoView.start()
            }
            splitText(workOut.muscle)
        }else{
            Toast.makeText(this, "Incorrect Data Recieved From Database", Toast.LENGTH_SHORT).show()
        }

        val setBtn: Button = findViewById(R.id.setTime)
        setBtn.setOnClickListener{
            setTime()
        }

        val startBtn : Button = findViewById(R.id.startBtn)
        startBtn.setOnClickListener {
            setUp()
        }

        val resetBtn : Button = findViewById(R.id.resetBtn)
        resetBtn.setOnClickListener {
            resetTimer()
        }
    }

    private fun splitText(txt: String){
        val str = txt.split(",").toTypedArray()
        var newStr = "Muscles worked: "
        for(teststr in str){
            newStr = "$newStr\n*$teststr"
        }
        binding.textView2.text = newStr
    }

    // code to set timer
    private fun setUp(){
        val startBtn : Button = findViewById(R.id.startBtn)
        if(timeSelected > timeProgress){
            if(isStart){
                startBtn.text = getString(R.string.pause)
                startTimer(timeOffSet)
                isStart = false
            }else {
                isStart = true
                startBtn.text = getString(R.string.resume)
                pauseTimer()
            }
        }
    }
    // code to start the timer on button click
    private fun startTimer(pauseTimeL : Long){
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        timeCountdown = object : CountDownTimer(
            (timeSelected * 1000).toLong() - pauseTimeL * 1000, 1000){
            override fun onTick(p0: Long) {
                timeProgress++
                timeOffSet = timeSelected.toLong() - p0/1000
                progressBar.progress = timeSelected - timeProgress

                val displayTime : TextView = findViewById(R.id.displayTime)
                displayTime.text = (timeSelected - timeProgress).toString()
            }

            override fun onFinish() {
                resetTimer()
                Toast.makeText(this@WorkoutActivity, "Times Up!!", Toast.LENGTH_SHORT).show()
            }

        }.start()
    }
    // code to pause time
    private fun pauseTimer(){
        if(timeCountdown != null){
            timeCountdown!!.cancel()
        }
    }

    // code will reset the timer to 0
    private fun resetTimer(){
        if(timeCountdown != null){
            timeCountdown!!.cancel()
            timeProgress = 0
            timeSelected = 0
            timeOffSet = 0
            timeCountdown = null

            val startBtn : Button = findViewById(R.id.startBtn)
            startBtn.text = getString(R.string.start)
            isStart = true

            val progressBar = findViewById<ProgressBar>(R.id.progressBar)
            progressBar.progress = 0

            val displayTime : TextView = findViewById(R.id.displayTime)
            displayTime.text = "0"
        }
    }
    // to open set timer dialog
    private fun  setTime(){
        val timeDialogue = Dialog(this)
        timeDialogue.setContentView(R.layout.add_dialogue)
        val timeSet = timeDialogue.findViewById<EditText>(R.id.getTime)
        val displayTime : TextView = findViewById(R.id.displayTime)
        val startBtn : Button = findViewById(R.id.startBtn)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        timeDialogue.findViewById<Button>(R.id.setBtn).setOnClickListener {
            if(timeSet.text.isEmpty()){
                Toast.makeText(this, "Set Time", Toast.LENGTH_SHORT).show()
            }else {
                displayTime.text = timeSet.text
                startBtn.text = getString(R.string.start)
                timeSelected = timeSet.text.toString().toInt()
                progressBar.max = timeSelected
            }
            timeDialogue.dismiss()
        }
        timeDialogue.show()
    }

    override fun onDestroy(){
        super.onDestroy()
        if(timeCountdown != null){
            timeCountdown?.cancel()
            timeProgress = 0
        }
    }

}