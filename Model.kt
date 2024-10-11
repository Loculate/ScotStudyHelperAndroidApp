package com.example.studified

import android.graphics.Bitmap


data class Subject(
    var buttonText: String,
    val destinationActivity: Class<*>,
    val backgroundColour: Int,
    val image: Bitmap? = null
)

object Supplier {
    val subjects = mutableListOf<Subject>()

    fun createSubject(
        buttonText: String,
        destinationActivity: Class<*>,
        backgroundColour: Int,
        image: Bitmap? = null
    ) {

        val subject = Subject(buttonText, destinationActivity, backgroundColour, image)
        subjects.add(subject)
    }
}

data class NotesAndQuestions(
    val image: Bitmap? = null,
    var notesText: String,
    val notesDestinationActivity: Class<*>,
    var questionsText: String,
    val questionsDestinationActivity : Class<*>,
    val backgroundColour: Int,
)