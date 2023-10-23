package com.example.bodyworks

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bodyworks.databinding.ActivityWorkoutBinding

class WorkoutActivity : AppCompatActivity() {

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

        binding.videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.burpee_workout))
        binding.videoView.requestFocus()
        binding.videoView.start()

        binding.videoView.setOnCompletionListener {
            binding.videoView.start()
        }
        splitText(text)
    }

    private fun splitText(txt: String){
        val str = txt.split(",").toTypedArray()
        var newStr = "Muscles worked: "
        for(teststr in str){
            newStr = "$newStr\n\n*$teststr"
        }
        binding.textView2.text = newStr
    }
}