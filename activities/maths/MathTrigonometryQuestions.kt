package com.example.studified.activities.maths

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import com.example.studified.R
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.PI
import kotlin.math.sqrt

class MathTrigonometryQuestions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_trigonometry_questions)

        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            backNav()
        }


        // Setting up the first question panel
        val questionTextView1: TextView = findViewById(R.id.QuestionText1) as TextView
        val answerText1: TextView = findViewById(R.id.AnswerText1) as TextView
        val regenButton1: Button = findViewById(R.id.RegenerateButton1) as Button
        val revealAnswerButton1: Button = findViewById(R.id.RevealButton1) as Button

        val questionPanelOne: QuestionPanelData = QuestionPanelData(
            questionText = questionTextView1,
            answerText = answerText1,
            regenerateQuestionButton = regenButton1,
            revealAnswerButton = revealAnswerButton1,
            revealed = false,
            answer = ""
        )

        questionPanelOne.revealAnswerButton.setOnClickListener{
            if (questionPanelOne.revealed)
            {
                questionPanelOne.hideAnswer(questionPanelOne)
            }
            else if (questionPanelOne.revealed == false)
            {
                questionPanelOne.revealAnswer(questionPanelOne)
            }
        }

        questionPanelOne.regenerateQuestionButton.setOnClickListener {
            regenerateQuestion1(questionPanelOne)
        }

        regenerateQuestion1(questionPanelOne)

        // Setting up the second question panel :)
        val questionTextView2: TextView = findViewById(R.id.QuestionText2) as TextView
        val answerText2: TextView = findViewById(R.id.AnswerText2) as TextView
        val regenButton2: Button = findViewById(R.id.RegenerateButton2) as Button
        val revealAnswerButton2: Button = findViewById(R.id.RevealButton2) as Button

        val questionPanelTwo: QuestionPanelData = QuestionPanelData(
            questionText = questionTextView2,
            answerText = answerText2,
            regenerateQuestionButton = regenButton2,
            revealAnswerButton = revealAnswerButton2,
            revealed = false,
            answer = ""
        )

        questionPanelTwo.revealAnswerButton.setOnClickListener{
            if (questionPanelTwo.revealed)
            {
                questionPanelTwo.hideAnswer(questionPanelTwo)
            }
            else if (questionPanelTwo.revealed == false)
            {
                questionPanelTwo.revealAnswer(questionPanelTwo)
            }
        }

        questionPanelTwo.regenerateQuestionButton.setOnClickListener {
            regenerateQuestion2(questionPanelTwo)
        }

        regenerateQuestion2(questionPanelTwo)

        // Setting up the third question panel :)
        val questionTextView3: TextView = findViewById(R.id.QuestionText3) as TextView
        val answerText3: TextView = findViewById(R.id.AnswerText3) as TextView
        val regenButton3: Button = findViewById(R.id.RegenerateButton3) as Button
        val revealAnswerButton3: Button = findViewById(R.id.RevealButton3) as Button

        val questionPanelThree: QuestionPanelData = QuestionPanelData(
            questionText = questionTextView3,
            answerText = answerText3,
            regenerateQuestionButton = regenButton3,
            revealAnswerButton = revealAnswerButton3,
            revealed = false,
            answer = ""
        )

        questionPanelThree.revealAnswerButton.setOnClickListener{
            if (questionPanelThree.revealed)
            {
                questionPanelThree.hideAnswer(questionPanelThree)
            }
            else if (questionPanelThree.revealed == false)
            {
                questionPanelThree.revealAnswer(questionPanelThree)
            }
        }

        questionPanelThree.regenerateQuestionButton.setOnClickListener {
            regenerateQuestion3(questionPanelThree)
        }

        regenerateQuestion3(questionPanelThree)
    }

    private fun backNav()
    {
        val options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0)
        val intent = Intent(this, MathsPage::class.java)
        startActivity(intent, options.toBundle())
    }

    private fun regenerateQuestion1(questionPanel: QuestionPanelData)
    {
        questionPanel.hideAnswer(questionPanel)

        questionPanel.questionText.text = getQuestionAndSetAnswer1(questionPanel)
    }

    private fun getQuestionAndSetAnswer1(questionPanel: QuestionPanelData) : String
    {
        var question: String = ""

        var answerUnrounded = 0.0

        val randomAngleStarts = arrayListOf<Double>(90.0, 180.0, 270.0)
        val randomAngleAdditions = arrayListOf<Double>(30.0, 60.0)

        val cosAndSin = arrayListOf<String>("Cos", "Sin")
        val cosOrSin = cosAndSin[getRandomNumberFromRange(0, 1)]

        val randomAngle = randomAngleStarts[getRandomNumberFromRange(0, 2)] + randomAngleAdditions[getRandomNumberFromRange(0, 1)]
        val randomAngleInRadians = randomAngle / 180 * PI

        question = "1. Solve " + cosOrSin + "(" + randomAngle.toString() + "), to one decimal place."

        if (cosOrSin == "Sin")
        {
            answerUnrounded = sin(randomAngleInRadians)
        }
        else if (cosOrSin == "Cos")
        {
            answerUnrounded = cos(randomAngleInRadians)
        }

        questionPanel.answer = "The answer is " + String.format("%.1f", answerUnrounded)

        return question
    }

    private fun getRandomNumberFromRange(min: Int, max: Int) : Int
    {
        return (min..max).random()
    }

    private fun regenerateQuestion2(questionPanel: QuestionPanelData)
    {
        questionPanel.hideAnswer(questionPanel)

        questionPanel.questionText.text = getQuestionAndSetAnswer2(questionPanel)
    }

    private fun getQuestionAndSetAnswer2(questionPanel: QuestionPanelData) : String
    {
        var question: String = "2. Some values for p and q are given. "

        var unroundedAnswer: Double = 0.0

        val pTriangle: Triangle = getTriangle()
        val qTriangle: Triangle = getTriangle()

        val sinP: String = pTriangle.opposite.toString() + "/" + pTriangle.hypotenuse.toString() + "\n"
        val cosP: String = pTriangle.adjacent.toString() + "/" + pTriangle.hypotenuse.toString() + "\n"
        val sinQ: String = qTriangle.opposite.toString() + "/" + qTriangle.hypotenuse.toString() + "\n"
        val cosQ: String = qTriangle.adjacent.toString() + "/" + qTriangle.hypotenuse.toString() + "\n"

        val sinPDouble =(pTriangle.opposite / pTriangle.hypotenuse)
        val cosPDouble =(pTriangle.adjacent / pTriangle.hypotenuse)
        val sinQDouble =(qTriangle.opposite / qTriangle.hypotenuse)
        val cosQDouble =(qTriangle.adjacent / qTriangle.hypotenuse)

        question += "\n\nSin(p) = " + sinP + "Cos(p) = " + cosP + "Sin(q) = " + sinQ + "Cos(q) = " + cosQ

        val sinAndCos = arrayOf("Sin", "Cos")
        val sinOrCos = sinAndCos[(0..1).random()]

        val plusAndMinus = arrayOf("+", "-")
        val plusOrMinus = plusAndMinus[(0..1).random()]

        question += "\n\nFind the value of " + sinOrCos + "(p " + plusOrMinus + " q)."

        if (sinOrCos == "Sin" && plusOrMinus == "+")
        {
            unroundedAnswer = (sinPDouble * cosQDouble) + (cosPDouble * sinQDouble)
        }
        else if (sinOrCos == "Sin" && plusOrMinus == "-")
        {
            unroundedAnswer = (sinPDouble * cosQDouble) - (cosPDouble * sinQDouble)
        }
        else if (sinOrCos == "Cos" && plusOrMinus == "+")
        {
            unroundedAnswer = (cosPDouble * cosQDouble) - (sinPDouble * sinQDouble)
        }
        else if (sinOrCos == "Cos" && plusOrMinus == "-")
        {
            unroundedAnswer = (cosPDouble * cosQDouble) + (sinPDouble * sinQDouble)
        }

        questionPanel.answer = "The answer is " + String.format("%.2f", unroundedAnswer)

        return question
    }

    private fun getTriangle(): Triangle
    {
        val opposite: Double = (2..9).random().toDouble()
        val adjacent: Double = (2..9).random().toDouble()

        val hypotenuseSquared : Double = ((opposite * opposite) + (adjacent * adjacent))
        val hypotenuse: Double = sqrt(hypotenuseSquared)
        val roundedHypotenuse: Double = String.format("%.1f", hypotenuse).toDouble()

        return Triangle(
            hypotenuse = roundedHypotenuse,
            opposite = opposite,
            adjacent = adjacent
        )
    }

    private fun regenerateQuestion3(questionPanel: QuestionPanelData)
    {
        questionPanel.hideAnswer(questionPanel)

        questionPanel.questionText.text = getQuestionAndSetAnswer3(questionPanel)
    }

    private fun getQuestionAndSetAnswer3(questionPanel: QuestionPanelData) : String
    {
        var question = "3. Find the maximum and minimum values of the graph, "

        var maximum = 0
        var minimum = 0

        var maximumXValue = 0
        var minimumXValue = 0

        val multValue = getRandomNumberFromRange(2, 9)

        question += multValue.toString()

        val sinAndCos = arrayOf("Sin", "Cos")
        val sinOrCos = sinAndCos[(0..1).random()]

        question += sinOrCos + "(x "

        val plusAndMinus = arrayOf("+", "-")
        val plusOrMinus = plusAndMinus[(0..1).random()]

        question += plusOrMinus + " "

        val aValues = arrayOf(45, 90, 135, 180)
        val aValue = aValues[getRandomNumberFromRange(0, 3)]

        question += aValue.toString() + ")"

        val c = getRandomNumberFromRange(1, 5)
        val cPlusOrMinus = plusAndMinus[(0..1).random()]

        question += " " + cPlusOrMinus + " " + c.toString()  + ", for 0 < x < 360"

        if(cPlusOrMinus == "+")
        {
            maximum = multValue + c
            minimum = -multValue + c
        }
        else if(cPlusOrMinus == "-")
        {
            maximum = multValue - c
            minimum = -multValue - c
        }

        if(sinOrCos == "Cos")
        {
            maximumXValue = aValue
            minimumXValue = 180 + aValue
            if(plusOrMinus == "-")
            {
                maximumXValue = -aValue
                minimumXValue = 180 - aValue
            }

            if (minimumXValue < 0)
            {
                minimumXValue += 360
            }

            if (maximumXValue < 0)
            {
                maximumXValue += 360
            }

            if (minimumXValue > 360)
            {
                minimumXValue -= 360
            }

            if (maximumXValue > 360)
            {
                maximumXValue -= 360
            }
        }

        else if(sinOrCos == "Sin")
        {
            maximumXValue = 90 + aValue
            minimumXValue = 270 + aValue
            if(plusOrMinus == "-")
            {
                maximumXValue = 90 - aValue
                minimumXValue = 270 - aValue
            }

            if (minimumXValue < 0)
            {
                minimumXValue += 360
            }

            if (maximumXValue < 0)
            {
                maximumXValue += 360
            }

            if (minimumXValue > 360)
            {
                minimumXValue -= 360
            }

            if (maximumXValue > 360)
            {
                maximumXValue -= 360
            }
        }

        questionPanel.answer = "The maximum is " + maximum.toString() + " when x = " + maximumXValue + "\nThe minimum is " + minimum.toString() + " when x = " + minimumXValue

        return question
    }
}

private class Triangle(
    val hypotenuse: Double = 0.0,
    val opposite: Double = 0.0,
    val adjacent: Double = 0.0
)
{

}

public class QuestionPanelData(
    val questionText: TextView,
    val answerText: TextView,
    val revealAnswerButton: Button,
    val regenerateQuestionButton: Button,
    var revealed: Boolean,
    var answer: String
)
{
    public fun hideAnswer(questionPanel: QuestionPanelData)
    {
        questionPanel.answerText.text = ""
        questionPanel.revealAnswerButton.text = "Answer"
        questionPanel.revealed = false
    }

    public fun revealAnswer(questionPanel: QuestionPanelData)
    {
        questionPanel.answerText.text = questionPanel.answer.toString()
        questionPanel.revealAnswerButton.text = "hide"
        questionPanel.revealed = true
    }
}