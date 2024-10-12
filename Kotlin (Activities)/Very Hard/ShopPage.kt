package com.example.studified.activities.mainpages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.example.studified.R
import android.content.Context
import android.widget.EditText
import android.text.TextWatcher
import android.text.Editable
import android.widget.TextView
import android.os.Handler
import android.os.Looper
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.AdapterView
import android.content.SharedPreferences


class ShopPage : AppCompatActivity() {
    private val PREFS_NAME = "MyPrefsKey"
    private val PREF_KEY1 = "MyPrefKey1"
    private val PREF_KEY2 = "MyPrefKey2"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_page)

        // Retrieve the timer value from SharedPreferences
        val timerValue = getStoredTimerValue()

        // Round timer value to one decimal place
        val roundedTimerValue = String.format("%.1f", timerValue)

        // Update TextView with the retrieved timer value
        val hrsStudiedText: TextView = findViewById(R.id.HoursStudied)
        hrsStudiedText.text = "You have studied: $roundedTimerValue hours"

        setupSpinner(R.id.strongestDropdown, PREF_KEY1)
        setupSpinner(R.id.weakestDropdown, PREF_KEY2)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("username", "")
        val eTextUsername = findViewById<EditText>(R.id.editTextUsername)
        eTextUsername.setText(savedUsername)

        eTextUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed
            }

            override fun afterTextChanged(s: Editable?) {
                // Save username to SharedPreferences after user finishes typing
                val editor = sharedPreferences.edit()
                editor.putString("username", s.toString())
                editor.apply()
            }
        })

        val loadingWriting: TextView = findViewById(R.id.writingLoadingText)
        val originalText = "Here you can view the statistics for your account."
        val textArray = originalText.split("").toTypedArray()

        var index = 0
        val mainLooper = Looper.getMainLooper()
        Handler(mainLooper).postDelayed(object : Runnable {
            override fun run() {
                if (index < textArray.size) {
                    loadingWriting.append(textArray[index++])
                    Handler(mainLooper).postDelayed(this, 40)
                }
            }
        }, 500)
    }

    private fun setupSpinner(id: Int, key: String) {
        // Get the Spinner from the layout
        val spinner: Spinner = findViewById(id)

        // Create an ArrayAdapter using a string array and a default spinner layout
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.dropdown_items_array, R.layout.custom_dropdown_layout
        )

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.custom_dropdown_layout)

        // Apply the adapter to the Spinner
        spinner.adapter = adapter

        // Get the shared preferences
        val preferences: SharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        // Get the previously selected option from the shared preferences
        val previousSelection: String? = preferences.getString(key, "")

        // Set the Spinner to the previously selected option
        val spinnerPosition: Int = adapter.getPosition(previousSelection)
        spinner.setSelection(spinnerPosition)

        // Set a listener to be called when an item is selected in the Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Get the selected option
                val selectedOption: String = parent.getItemAtPosition(position).toString()

                // Save the selected option in the shared preferences
                val editor: SharedPreferences.Editor = preferences.edit()
                editor.putString(key, selectedOption)
                editor.apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
    }

    fun homeNavigation(view: View){
        val options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0)
        val intent = Intent(this, MainAct::class.java)
        startActivity(intent, options.toBundle())
    }

    fun statsNavigation(view: View){
        val options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0)
        val intent = Intent(this, StatsPage::class.java)
        startActivity(intent, options.toBundle())
    }

    private fun getStoredTimerValue(): Float {
        val sharedPreferences = getSharedPreferences("TimerPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getFloat("timerValue", 0f) // Default value is 0f if no value is found
    }

}
