package com.example.studified.activities.computing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.example.studified.R
import com.example.studified.activities.english.EnglishPage
import com.example.studified.activities.mainpages.MainAct

class ComputingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_computing_page)
    }

    fun backHomeNavigationComputing(view: View){
        val options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0)
        val intent = Intent(this, MainAct::class.java)
        startActivity(intent, options.toBundle())
    }
}