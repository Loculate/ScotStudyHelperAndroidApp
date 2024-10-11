package com.example.studified.activities.english

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.example.studified.R

class EnglishPracticePapers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_english_practice_papers)
    }

    fun backHomeNavigationEnglishPracticePapers(view: View) {
        val options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0)
        val intent = Intent(this, EnglishPage::class.java)
        startActivity(intent, options.toBundle())
    }
}