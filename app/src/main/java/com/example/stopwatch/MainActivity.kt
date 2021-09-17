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
    var displayedTime = 0L
    
    companion object{
        //all static constants go here
        val TAG = "MainActivity"
        val BUNDLE_DISPLAYED_TIME = "displayed time"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        setContentView(R.layout.activity_main)

        wireWidgits()

        displayedTime = savedInstanceState?.getLong(BUNDLE_DISPLAYED_TIME) ?: 0L
        timer.base = SystemClock.elapsedRealtime() - displayedTime



        startStopButton.text= "Start"
        resetButton.text = "Reset"

        startStopButton.setOnClickListener {
            if(!isRunning){
                startT()
            }
            else{
                stopT()
            }
        }

        resetButton.setOnClickListener {
            displayedTime = 0L
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
        timer.base = SystemClock.elapsedRealtime() - displayedTime
        startStopButton.text = "Stop"
        timer.start()
        isRunning = true
    }

    fun stopT(){
        updateDisplayedTime()
        startStopButton.text = "Start"
        timer.stop()
        isRunning = false
    }

    /*fun newBase(){
        var curBase = timer.base
        var curTime = SystemClock.elapsedRealtime() - curBase
        timer.base = curTime - stoppedTime + curBase
    }
     */

    fun updateDisplayedTime() {
        if(isRunning) {
            displayedTime = SystemClock.elapsedRealtime() - timer.base
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        updateDisplayedTime()
        outState.putLong(BUNDLE_DISPLAYED_TIME, displayedTime)
        if (isRunning){
            timer.start()
        }
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