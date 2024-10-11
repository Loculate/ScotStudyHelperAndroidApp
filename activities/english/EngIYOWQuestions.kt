package com.example.studified.activities.english

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.example.studified.R

class EngIYOWQuestions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eng_iyowquestions)

        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            backNav()
        }
    }

    private fun backNav()
    {
        val options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0)
        val intent = Intent(this, EnglishPage::class.java)
        startActivity(intent, options.toBundle())
    }
}