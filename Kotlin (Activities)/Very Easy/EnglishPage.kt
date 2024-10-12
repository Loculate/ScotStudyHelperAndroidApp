package com.example.studified.activities.english

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.studified.NotesAndQuestions
import com.example.studified.activities.mainpages.MainAct
import com.example.studified.R
import com.example.studified.activities.mainpages.getBitmapFromDrawableResource
import com.example.studified.datahelpers.NotesAndQuestionsAdapter

class EnglishPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_english_page)

        val notesAndQuestionsDataset = arrayOf(
            NotesAndQuestions(
            getBitmapFromDrawableResource(this, R.drawable.ays_icon),
            "Analysis Notes",
            EngAnalysis::class.java,
            "Analysis Questions",
            EngAnalysisQuestions::class.java,
            Color.argb(100, 100, 100, 100)
        ),
            NotesAndQuestions(
                getBitmapFromDrawableResource(this, R.drawable.ten_marker),
                "Ten Marker Notes",
                EngTenMarker::class.java,
                "Ten Marker Questions",
                EngTenMarkerQuestions::class.java,
                Color.argb(100, 100, 100, 100)
            ),

            NotesAndQuestions(
                getBitmapFromDrawableResource(this, R.drawable.iyow_icon),
                "In Your Own Words Notes",
                EngIYOW::class.java,
                "In Your Own Words Questions",
                EngIYOWQuestions::class.java,
                Color.argb(100, 100, 100, 100)
            ),

            NotesAndQuestions(
                getBitmapFromDrawableResource(this, R.drawable.five_marker),
                "5 Marker Notes",
                EngFiveMarker::class.java,
                "5 Marker Questions",
                EngFiveMarkerQuestions::class.java,
                Color.argb(100, 100, 100, 100)
            )
        )

        val pracPapersDataset = arrayOf(NotesAndQuestions(
            getBitmapFromDrawableResource(this, R.drawable.practice_questions_icon),
            "Practice Paper One",
            EngPastPaper2018::class.java,
            "Practice Paper One Answers",
            EngPastPaper2018Answers::class.java,
            Color.argb(100, 100, 100, 100)
        ),
            NotesAndQuestions(
                getBitmapFromDrawableResource(this, R.drawable.icon_one),
                "Practice Paper Two",
                EngPastPaper2019::class.java,
                "Practice Paper Two Answers",
                EngPastPaper2019Answers::class.java,
                Color.argb(100, 100, 100, 100)
            ),

            NotesAndQuestions(
                getBitmapFromDrawableResource(this, R.drawable.icon_two),
                "Practice Paper Three",
                EngPastPaper2022::class.java,
                "Practice Paper Three Answers",
                EngPastPaper2022Answers::class.java,
                Color.argb(100, 100, 100, 100)
            ),

            NotesAndQuestions(
                getBitmapFromDrawableResource(this, R.drawable.icon_three),
                "Practice Paper Four",
                EngPastPaper2023::class.java,
                "Practice Paper Four Answers",
                EngPastPaper2023Answers::class.java,
                Color.argb(100, 100, 100, 100)
            )
        )

        val topicRecView: RecyclerView = findViewById(R.id.NotesAndQuestionsRecView)
        val notesAdapter = NotesAndQuestionsAdapter(notesAndQuestionsDataset)
        topicRecView.adapter = notesAdapter
        topicRecView.scrollToPosition(NotesAndQuestionsAdapter.getStartPosition(notesAndQuestionsDataset.size))

        val pracPapersRecView: RecyclerView = findViewById(R.id.PracticePaperRecView)
        val pracAdapter = NotesAndQuestionsAdapter(pracPapersDataset)
        pracPapersRecView.adapter = pracAdapter
        pracPapersRecView.scrollToPosition(NotesAndQuestionsAdapter.getStartPosition(pracPapersDataset.size))


        val loadingWriting: TextView = findViewById(R.id.writingLoadingText)
        val originalText = "Delve into the interesting world of English literature with one of the activities below!"
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

    fun backHomeNavigationEnglish(view: View){
        val options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0)
        val intent = Intent(this, MainAct::class.java)
        startActivity(intent, options.toBundle())
    }
}