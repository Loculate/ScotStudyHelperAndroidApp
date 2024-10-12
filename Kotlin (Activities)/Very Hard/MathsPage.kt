package com.example.studified.activities.maths

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
import com.example.studified.R
import com.example.studified.activities.english.EngAnalysis
import com.example.studified.activities.english.EngAnalysisQuestions
import com.example.studified.activities.english.EngFiveMarker
import com.example.studified.activities.english.EngFiveMarkerQuestions
import com.example.studified.activities.mainpages.MainAct
import com.example.studified.activities.mainpages.getBitmapFromDrawableResource
import com.example.studified.datahelpers.NotesAndQuestionsAdapter

class MathsPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maths_page)

        val notesAndQuestionsDataset = arrayOf(
            NotesAndQuestions(
                getBitmapFromDrawableResource(this, R.drawable.math_infinity_image),
                "Logarithms Notes",
                MathLogAndExp::class.java,
                "Logarithms Questions",
                MathLogAndExpQuestions::class.java,
                Color.argb(100, 100, 100, 100)
            ),

            NotesAndQuestions(
                getBitmapFromDrawableResource(this, R.drawable.maths_pi_icon),
                "Trigonometry Notes",
                MathTrigonometry::class.java,
                "Trigonometry Questions",
                MathTrigonometryQuestions::class.java,
                Color.argb(100, 100, 100, 100)
            ),

            NotesAndQuestions(
                getBitmapFromDrawableResource(this, R.drawable.math_shapes_image),
                "Functions Notes",
                FunctionsAndGraphs::class.java,
                "Functions Questions",
                FunctionsAndGraphsQuestions::class.java,
                Color.argb(100, 100, 100, 100)
            ),

            NotesAndQuestions(
                getBitmapFromDrawableResource(this, R.drawable.weird_cube_image),
                "Vectors Notes",
                MathVectors::class.java,
                "Vectors Questions",
                MathVectorsQuestions::class.java,
                Color.argb(100, 100, 100, 100)
            )
        )

        val pracPapersDataset = arrayOf(NotesAndQuestions(
            getBitmapFromDrawableResource(this, R.drawable.icon_one),
            "Practice Paper One",
            MatPracticePaperOne::class.java,
            "Practice Paper One Answers",
            MatPracticePaperOneAnswers::class.java,
            Color.argb(100, 100, 100, 100)
        ),
            NotesAndQuestions(
                getBitmapFromDrawableResource(this, R.drawable.abacus_image),
                "Practice Paper Two",
                MatPracticePaperTwo::class.java,
                "Practice Paper Two Answers",
                MatPracticePaperTwoAnswers::class.java,
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
        val originalText = "Explore the fascinating patterns and logic of mathematics with one of the tasks below!"
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

    fun backHomeNavigationMaths(view: View){
        val options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0)
        val intent = Intent(this, MainAct::class.java)
        startActivity(intent, options.toBundle())
    }
}