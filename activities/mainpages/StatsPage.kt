package com.example.studified.activities.mainpages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.example.studified.datahelpers.DataHelper
import com.example.studified.databinding.ActivityStatsPageBinding
import java.util.Timer
import java.util.*
import android.widget.TextView
import com.example.studified.R
import android.content.Context

class StatsPage : AppCompatActivity() {

    lateinit var binding: ActivityStatsPageBinding
    lateinit var dataHelper: DataHelper

    private val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatsPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataHelper = DataHelper(applicationContext)

        binding.start.setOnClickListener{startStopAction()}
        binding.reset.setOnClickListener{resetAction(true)}

        if(dataHelper.timerCounting())
        {
            startTimer()
        }

        else
        {
            stopTimer()
            if(dataHelper.startTime() != null && dataHelper.stopTime() != null)
            {
                val time = Date().time - calcRestartTime().time
                binding.timer.text = timeStringFromLong(time)
            }
        }

        timer.scheduleAtFixedRate(TimeTask(), 0, 500)

        val loadingWriting: TextView = findViewById(R.id.writingLoadingText)
        val originalText = "Welcome to the timer page, here you can track your session durations using the timer below!"
        val textArray = originalText.split("").toTypedArray()

        var index = 0
        val mainLooper = Looper.getMainLooper()
        Handler(mainLooper).postDelayed(object : Runnable {
            override fun run() {
                if (index < textArray.size){
                    loadingWriting.append(textArray[index++])
                    Handler(mainLooper).postDelayed(this, 40)
                }
            }
        }, 500)

    }

    private inner class TimeTask: TimerTask()
    {
        override fun run() {
            if(dataHelper.timerCounting())
            {
                val currentTime = Date().time
                val startTime = dataHelper.startTime()!!.time
                val elapsedTime = currentTime - startTime

                runOnUiThread() {
                    if (elapsedTime >= 2 * 60 * 60 * 1000) {
                        resetAction(false)
                    } else {
                        // Use runOnUiThread to update UI on the main thread
                        binding.timer.text = timeStringFromLong(elapsedTime)
                    }
                }
            }
        }
    }

    private fun resetAction(addToStats: Boolean)
    {
        val currentTimerValue = dataHelper.startTime()?.let {
            Date().time - it.time
        } ?: 0L // If start time is null, use 0L

        // Convert timer value to hours
        val currentTimerInHours = currentTimerValue.toDouble() / (1000 * 60 * 60)

        // Add the new timer value to the existing value stored in SharedPreferences
        val updatedTimerValue = currentTimerInHours + getStoredTimerValue()

        // Save the timer value to SharedPreferences if reset by user
        if (addToStats) {
            saveTimerValueToSharedPreferences(updatedTimerValue.toFloat())
        }

        dataHelper.setStopTime(null)
        dataHelper.setStartTime(null)
        stopTimer()
        binding.timer.text = timeStringFromLong(0)
    }

    private fun stopTimer()
    {
        dataHelper.setTimerCounting(false)
        binding.start.text = "Start"
    }

    private fun startTimer()
    {
        dataHelper.setTimerCounting(true)
        binding.start.text = "Stop"
    }

    private fun startStopAction()
    {
        if(dataHelper.timerCounting())
        {
            dataHelper.setStopTime(Date())
            stopTimer()
        }
        else
        {
            if(dataHelper.stopTime() != null)
            {
                dataHelper.setStartTime(calcRestartTime())
                dataHelper.setStopTime(null)
            }
            else
            {
                dataHelper.setStartTime(Date())
            }
            startTimer()
        }
    }

    private fun calcRestartTime(): Date
    {
        val diff = dataHelper.startTime()!!.time - dataHelper.stopTime()!!.time
        return Date(System.currentTimeMillis() + diff)
    }

    private fun timeStringFromLong(ms: Long): String
    {
        val seconds = (ms / 1000) % 60
        val minutes = (ms / (1000 * 60) % 60)
        val hours = (ms / (1000 * 60 * 60) % 24)

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hours: Long, minutes: Long, seconds: Long): String
    {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    private fun saveTimerValueToSharedPreferences(timerValue: Float) {
        val sharedPreferences = getSharedPreferences("TimerPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putFloat("timerValue", timerValue)
        editor.apply()
    }

    private fun getStoredTimerValue(): Float {
        val sharedPreferences = getSharedPreferences("TimerPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getFloat("timerValue", 0f) // Default value is 0f if no value is found
    }

    fun homeNavigation(view: View){
        val options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0)
        val intent = Intent(this, MainAct::class.java)
        startActivity(intent, options.toBundle())
    }

    fun shopNavigation(view: View){
        val options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0)
        val intent = Intent(this, ShopPage::class.java)
        startActivity(intent, options.toBundle())
    }
}