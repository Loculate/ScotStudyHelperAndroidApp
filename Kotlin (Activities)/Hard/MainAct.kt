package com.example.studified.activities.mainpages
import com.example.studified.activities.maths.MathsPage
import com.example.studified.activities.english.EnglishPage
import com.example.studified.activities.physics.PhysicsPage
import com.example.studified.activities.computing.ComputingPage
import com.example.studified.activities.engineering.EngineeringPage
import androidx.core.app.ActivityOptionsCompat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studified.R
import com.example.studified.datahelpers.SubjectAdapter
import com.example.studified.Supplier
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.widget.TextView

class MainAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        val adapter = SubjectAdapter(this, Supplier.subjects)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        val mathsBitmap = getBitmapFromDrawableResource(this, R.drawable.maths_icon)
        val englishBitmap = getBitmapFromDrawableResource(this, R.drawable.english_icon)
        val physicsBitmap = getBitmapFromDrawableResource(this, R.drawable.physics_icon)
        val computingBitmap = getBitmapFromDrawableResource(this, R.drawable.computing_icon)
        val engineeringBitmap = getBitmapFromDrawableResource(this, R.drawable.engineering_icon)

        if (Supplier.subjects.isEmpty()) {

            Supplier.createSubject(
                "Maths",
                MathsPage::class.java,
                Color.argb(100, 120, 120, 50),
                mathsBitmap
            )

            Supplier.createSubject(
                "English",
                EnglishPage::class.java,
                Color.argb(100, 0, 50, 20),
                englishBitmap
            )

            /* Work in Progress :(
            Supplier.createSubject(
                "Physics",
                PhysicsPage::class.java,
                Color.argb(100, 125, 5, 5),
                physicsBitmap
            )
            Supplier.createSubject(
                "Computing",
                ComputingPage::class.java,
                Color.argb(100, 100, 30, 190),
                computingBitmap
            )
            Supplier.createSubject(
                "Engineering",
                EngineeringPage::class.java,
                Color.argb(100, 10, 150, 150),
                engineeringBitmap
            )
            */
        }

        val loadingWriting: TextView = findViewById(R.id.writingLoadingText)
        val originalText = "Welcome to the main page of Scot Study Helper, please select a topic to start your learning journey!"
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

    fun statsNavigation(view: View){
        val options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0)
        val intent = Intent(this, StatsPage::class.java)
        startActivity(intent, options.toBundle())
    }

    fun shopNavigation(view: View){
        val options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0)
        val intent = Intent(this, ShopPage::class.java)
        startActivity(intent, options.toBundle())
    }
}

fun getBitmapFromDrawableResource(context: Context, drawableResId: Int): Bitmap {
    return BitmapFactory.decodeResource(context.resources, drawableResId)
}
// Look at chatGPT and need to only initialise the list once :)