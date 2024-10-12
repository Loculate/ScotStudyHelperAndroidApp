package com.example.studified.activities.maths

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import com.example.studified.R
import kotlin.math.sqrt
import kotlin.math.acos

class MathVectorsQuestions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_vectors_questions)

        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            backNav()
        }

        // Setting up the first question panel :)
        val questionTextView1: TextView = findViewById(R.id.QuestionText1) as TextView
        val answerText1: TextView = findViewById(R.id.AnswerText1) as TextView
        val regenButton1: Button = findViewById(R.id.RegenerateButton1) as Button
        val revealAnswerButton1: Button = findViewById(R.id.RevealButton1) as Button

        val questionPanel1: QuestionPanelVectors = QuestionPanelVectors(
            questionText = questionTextView1,
            answerText = answerText1,
            regenerateQuestionButton = regenButton1,
            revealAnswerButton = revealAnswerButton1,
            revealed = false,
            answer = ""
        )

        questionPanel1.revealAnswerButton.setOnClickListener {
            if(questionPanel1.revealed)
            {
                questionPanel1.hideAnswer(questionPanel1)
            }
            else if (questionPanel1.revealed == false)
            {
                questionPanel1.revealAnswer(questionPanel1)
            }
        }

        questionPanel1.regenerateQuestionButton.setOnClickListener {
            questionPanel1.regenerateQuestion(questionPanel1, getQuestionAndAnswer1(questionPanel1))
        }

        questionPanel1.regenerateQuestion(questionPanel1, getQuestionAndAnswer1(questionPanel1))

        // Setting up the second question panel
        val questionTextView2: TextView = findViewById(R.id.QuestionText2) as TextView
        val answerText2: TextView = findViewById(R.id.AnswerText2) as TextView
        val regenButton2: Button = findViewById(R.id.RegenerateButton2) as Button
        val revealAnswerButton2: Button = findViewById(R.id.RevealButton2) as Button

        val questionPanel2: QuestionPanelVectors = QuestionPanelVectors(
            questionText = questionTextView2,
            answerText = answerText2,
            regenerateQuestionButton = regenButton2,
            revealAnswerButton = revealAnswerButton2,
            revealed = false,
            answer = ""
        )

        questionPanel2.revealAnswerButton.setOnClickListener {
            if(questionPanel2.revealed)
            {
                questionPanel2.hideAnswer(questionPanel2)
            }
            else if (questionPanel2.revealed == false)
            {
                questionPanel2.revealAnswer(questionPanel2)
            }
        }

        questionPanel2.regenerateQuestionButton.setOnClickListener {
            questionPanel2.regenerateQuestion(questionPanel2, getQuestionAndAnswer2(questionPanel2))
        }

        questionPanel2.regenerateQuestion(questionPanel2, getQuestionAndAnswer2(questionPanel2))

        // Setting up the third question panel
        val questionTextView3: TextView = findViewById(R.id.QuestionText3) as TextView
        val answerText3: TextView = findViewById(R.id.AnswerText3) as TextView
        val regenButton3: Button = findViewById(R.id.RegenerateButton3) as Button
        val revealAnswerButton3: Button = findViewById(R.id.RevealButton3) as Button

        val questionPanel3: QuestionPanelVectors = QuestionPanelVectors(
            questionText = questionTextView3,
            answerText = answerText3,
            regenerateQuestionButton = regenButton3,
            revealAnswerButton = revealAnswerButton3,
            revealed = false,
            answer = ""
        )

        questionPanel3.revealAnswerButton.setOnClickListener {
            if(questionPanel3.revealed)
            {
                questionPanel3.hideAnswer(questionPanel3)
            }
            else if (questionPanel3.revealed == false)
            {
                questionPanel3.revealAnswer(questionPanel3)
            }
        }

        questionPanel3.regenerateQuestionButton.setOnClickListener {
            questionPanel3.regenerateQuestion(questionPanel3, getQuestionAndAnswer3(questionPanel3))
        }

        questionPanel3.regenerateQuestion(questionPanel3, getQuestionAndAnswer3(questionPanel3))
    }

    private fun backNav()
    {
        val options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0)
        val intent = Intent(this, MathsPage::class.java)
        startActivity(intent, options.toBundle())
    }

    private fun getRandomNumberFromRange(min: Int, max: Int): Int {
        return (min..max).random()
    }

    private fun getQuestionAndAnswer1(questionPanel: QuestionPanelVectors) : String
    {
        var question = "1. The values of vectors \n\nA("
        var answer = ""

        var pointA = Vector()
        pointA = pointA.makeVectors(-5, 5)

        question += pointA.x.toString() + ", " + pointA.y + ", " + pointA.z + "),"

        var pointB = Vector()
        pointB = pointB.makeVectors(-5, 5)

        question += "\nB(" + pointB.x.toString() + ", " + pointB.y + ", " + pointB.z + "),"


        val cSplitRatio = getRandomNumberFromRange(2, 4)

        val pointCX = pointB.x + ((pointB.x - pointA.x) * cSplitRatio)
        val pointCY = pointB.y + ((pointB.y - pointA.y) * cSplitRatio)
        val pointCZ = pointB.z + ((pointB.z - pointA.z) * cSplitRatio)

        val pointC = Vector(pointCX, pointCY, pointCZ)

        question += "\nC(" + pointC.x.toString() + ", " + pointC.y + ", " + pointC.z + ") are known."

        question += "\n\nHence, prove that the vectors AB and BC are collinear."

        answer = "• Since " + cSplitRatio.toString() + "AB = BC the vectors are parallel. \n\n• Because both vectors share a common point, they are collinear. "

        questionPanel.answer = answer

        return question
    }

    private fun getQuestionAndAnswer2(questionPanel: QuestionPanelVectors) : String
    {
        var question = "2. Since points \n\nA("

        var pointA = Vector()
        pointA = pointA.makeVectors(-5, 5)

        question += pointA.x.toString() + ", " + pointA.y + ", " + pointA.z + "),"

        var pointB = Vector()
        pointB = pointB.makeVectors(-5, 5)

        val cSplitRatio = getRandomNumberFromRange(2, 4)

        val pointCX = pointB.x + ((pointB.x - pointA.x) * cSplitRatio)
        val pointCY = pointB.y + ((pointB.y - pointA.y) * cSplitRatio)
        val pointCZ = pointB.z + ((pointB.z - pointA.z) * cSplitRatio)

        val pointC = Vector(pointCX, pointCY, pointCZ)

        question += "\nC(" + pointC.x.toString() + ", " + pointC.y + ", " + pointC.z + ")"

        question += "\nAre split by b in the ratio 1:" + cSplitRatio.toString() + ", \n\nFind the coordinates of point B."

        val answer = "B = (" + pointB.x.toString() + ", " + pointB.y + ", " + pointB.z + ")"

        questionPanel.answer = answer

        return question
    }

    private fun getQuestionAndAnswer3(questionPanel: QuestionPanelVectors) : String
    {
        var question = "3. Find the angles between vectors "
        var answer = ""

        var vectorA = Vector()
        vectorA = vectorA.makeVectors(-5, 5)

        val vectorAMagnitudeSquared = ((vectorA.x * vectorA.x) + (vectorA.y * vectorA.y) + (vectorA.z * vectorA.z)).toDouble()
        val vectorAMagnitude = sqrt(vectorAMagnitudeSquared)

        answer += "│a│ = " + String.format("%.1f", vectorAMagnitude)

        question += "\n\nA(" + vectorA.x.toString() + ", " + vectorA.y + ", " + vectorA.z + "),"

        var vectorB = Vector()
        vectorB = vectorB.makeVectors(-5, 5)

        val vectorBMagnitudeSquared = ((vectorB.x * vectorB.x) + (vectorB.y * vectorB.y) + (vectorB.z * vectorB.z)).toDouble()
        val vectorBMagnitude = sqrt(vectorBMagnitudeSquared)

        answer += "\n│b│ = " + String.format("%.1f", vectorBMagnitude)

        question += "\nB(" + vectorB.x.toString() + ", " + vectorB.y + ", " + vectorB.z + "),"

        val dotProduct : Double = ((vectorA.x * vectorB.x) + (vectorA.y * vectorB.y) + (vectorA.z * vectorB.z)).toDouble()

        answer += "\n\na.b = " + dotProduct.toString()

        val cosTheta = dotProduct / (vectorAMagnitude * vectorBMagnitude)
        val theta = Math.toDegrees(acos(cosTheta))

        answer += "\n\nθ = " + String.format("%.1f", theta)

        questionPanel.answer = answer

        return question
    }
}

public class Vector(
    val x: Int = 0,
    val y: Int = 0,
    val z: Int = 0
)
{
    private fun getRandomNumberFromRange(min: Int, max: Int): Int {
        return (min..max).random()
    }

    public fun makeVectors(min: Int, max: Int): Vector {
        val x = getRandomNumberFromRange(min, max)
        val y = getRandomNumberFromRange(min, max)
        val z = getRandomNumberFromRange(min, max)

        return Vector(x = x, y = y, z = z)
    }
}

public class QuestionPanelVectors(
    val questionText: TextView,
    val answerText: TextView,
    val revealAnswerButton: Button,
    val regenerateQuestionButton: Button,
    var revealed: Boolean,
    var answer: String
)
{
    public fun hideAnswer(questionPanel: QuestionPanelVectors)
    {
        questionPanel.answerText.text = ""
        questionPanel.revealAnswerButton.text = "Answer"
        questionPanel.revealed = false
    }

    public fun revealAnswer(questionPanel: QuestionPanelVectors)
    {
        questionPanel.answerText.text = questionPanel.answer.toString()
        questionPanel.revealAnswerButton.text = "hide"
        questionPanel.revealed = true
    }

    public fun regenerateQuestion(questionPanel: QuestionPanelVectors, question: String)
    {
        questionPanel.hideAnswer(questionPanel)

        questionPanel.questionText.text = question
    }
}
