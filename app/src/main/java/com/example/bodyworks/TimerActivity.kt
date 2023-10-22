package com.example.bodyworks

import android.app.Dialog
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TimerActivity : AppCompatActivity() {

    private var timeSelected : Int = 0
    private var timeCountdown : CountDownTimer? = null
    private var timeProgress = 0
    private var timeOffSet : Long = 0
    private var isStart = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

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
                Toast.makeText(this@TimerActivity, "Times Up!!", Toast.LENGTH_SHORT).show()
            }

        }.start()
    }

    private fun pauseTimer(){
        if(timeCountdown != null){
            timeCountdown!!.cancel()
        }
    }


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
