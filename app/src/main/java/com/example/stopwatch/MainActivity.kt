package com.example.stopwatch

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Chronometer

class MainActivity: AppCompatActivity() {

    lateinit var timer : Chronometer
    lateinit var startStopButton : Button
    lateinit var resetButton : Button
    var isRunning = false
    var stoppedTime = 0
    var isStopped = false


    companion object{
        //all static constants go here
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        setContentView(R.layout.activity_main)

        wireWidgits()

        startStopButton.text= "Start"
        resetButton.text = "Reset"

        timer.base = SystemClock.elapsedRealtime()

        startStopButton.setOnClickListener {
            if(!isRunning){
                startT()
            }
            else{
                stopT()
            }
        }

        resetButton.setOnClickListener {
            timer.base = SystemClock.elapsedRealtime()
            if(!isRunning){
                stopT()
            }
            else{
                startT()
            }


        }

    }

    fun startT(){
        newBase()
        startStopButton.text = "Stop"
        timer.start()
        isRunning = true
    }

    fun stopT(){
        stoppedTime = (SystemClock.elapsedRealtime() - timer.base).toInt()
        startStopButton.text = "Start"
        timer.stop()
        isRunning = false
    }

    fun newBase(){
        var curBase = timer.base
        var curTime = SystemClock.elapsedRealtime() - curBase
        timer.base = curTime - stoppedTime + curBase
    }
    
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    fun wireWidgits(){
        timer = findViewById(R.id.chronometer_main_stopwatch)
        startStopButton = findViewById(R.id.button_main_startStop)
        resetButton = findViewById(R.id.button_main_reset)
    }
}