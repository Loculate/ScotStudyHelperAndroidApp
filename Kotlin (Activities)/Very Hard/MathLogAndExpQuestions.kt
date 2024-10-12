package com.example.studified.activities.maths

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.example.studified.R
import android.widget.TextView
import android.widget.Button
import kotlin.math.pow

class MathLogAndExpQuestions : AppCompatActivity() {
    var answer: Double = 0.0
    var revealed: Boolean = false
    var incOrDecCheck: Int = 1
    var globAnimal: String = "turtle"

    var revealedTwo: Boolean = false
    var answerTwo: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_log_and_exp_questions)

        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            backNav()
        }

        // Questions Panel One

        val firstRegenerate: Button = findViewById (R.id.RegenerateButtonOne) as Button
        firstRegenerate.setOnClickListener{
            regenerateQuestion()
        }

        val repeatButton: Button = findViewById (R.id.RevealButton) as Button
        val answerText: TextView = findViewById (R.id.firstAnswer) as TextView
        repeatButton.setOnClickListener {
            if (revealed)
            {
                hideTwo(answerText)
                revealed = false
                repeatButton.text = "Answer"
            }
            else
            {
                revealAnswer(answerText)
                revealed = true
                repeatButton.text = "Hide"
            }
        }

        // Question Panel Two
        val secondQuestion: TextView = findViewById(R.id.secondQuestion) as TextView
        val repeatButtonTwo: Button = findViewById (R.id.secondRevealButton) as Button
        val answerTextTwo: TextView = findViewById (R.id.secondAnswer) as TextView

        val secondRegenerate: Button = findViewById (R.id.RegenerateButtonTwo) as Button
        secondRegenerate.setOnClickListener{
            regenerateQuestionTwo(repeatButtonTwo, secondQuestion, answerTextTwo)
        }

        repeatButtonTwo.setOnClickListener {
            if (revealedTwo)
            {
                hideTwo(answerTextTwo)
                revealedTwo = false
                repeatButtonTwo.text = "Answer"
            }
            else
            {
                revealAnswerTwo(answerTextTwo)
                revealedTwo = true
                repeatButtonTwo.text = "Hide"
            }
        }

        regenerateQuestionTwo(repeatButtonTwo, secondQuestion, answerTextTwo)
        regenerateQuestion()
    }

    private fun backNav()
    {
        val options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0)
        val intent = Intent(this, MathsPage::class.java)
        startActivity(intent, options.toBundle())
    }

    private fun regenerateQuestion()
    {
        val repeatButton: Button = findViewById (R.id.RevealButton) as Button
        repeatButton.text = "Answer"

        revealed = false

        val answerText: TextView = findViewById (R.id.firstAnswer) as TextView
        hideAnswer(answerText)

        val popNumber = ((10.. 100).random() * 100)

        val incOrDec = getIncOrDec()
        val percent = getPercent().toDouble()
        val years = getYears().toDouble()
        val animal = getAnimalFromArray()

        val sentence = "1. The " + animal + " population " + getPlaceFromArray() + " " + incOrDec + " by " + percent + "% per year. If the starting population is " + popNumber + " what will it be in " + years + " years?"

        // Find the text view by its id
        val firstQuestion: TextView = findViewById(R.id.firstQuestion) as TextView

        // Call the setText method and pass the sentence
        firstQuestion.setText(sentence)

        val multiplier = (1 + (percent  / 100) * incOrDecCheck)


        val questionAnswer = popNumber * (multiplier.pow(years))

        val roundedQuestionAnswer: Double = String.format("%.0f", questionAnswer).toDouble()

        answer = roundedQuestionAnswer
        globAnimal = animal
    }

    private fun getAnimalFromArray() : String
    {
        val animals = arrayOf(
            "dog",
            "cat",
            "elephant",
            "lion",
            "tiger",
            "giraffe",
            "zebra",
            "panda",
            "penguin",
            "bear",
            "monkey",
            "crocodile",
            "snake",
            "turtle",
            "whale",
            "dolphin",
            "shark",
            "eagle",
            "parrot",
            "hummingbird"
        )
        return animals[(0 .. 19).random()]
    }

    private fun getPlaceFromArray() : String
    {
        val places = arrayOf(
            "on an island",
            "in a country",
            "in a city",
            "in a town"
        )

        return places[(0..3).random()]
    }

    private fun getIncOrDec() : String
    {
        val incAndDec = arrayOf(
            "increases",
            "decreases"
        )

        val incOrDecNum = (0..1).random()

        if (incOrDecNum == 0)
        {
            incOrDecCheck = 1
        }
        else if (incOrDecNum == 1)
        {
            incOrDecCheck = -1
        }

        return incAndDec[incOrDecNum]
    }

    private fun getPercent() : Int
    {
        val percentNum = (10.. 50).random()
        return percentNum
    }

    private fun getYears() : Int
    {
        val years = (2..8).random()
        return years
    }

    private fun hideAnswer(textView: TextView)
    {
        textView.setText("")
    }

    private fun revealAnswer(textView: TextView)
    {
        val finAnswer = "The answer is " + answer + " " + globAnimal + "s"
        textView.setText(finAnswer)
    }

    private fun regenerateQuestionTwo(revealButtonTwo: Button, questionText: TextView, answerTextTwo: TextView)
    {
        revealButtonTwo.setText("Answer")
        revealedTwo = false
        hideTwo(answerTextTwo)

        questionText.setText(getQuestionTwo())
    }

    private fun hideTwo(answerText: TextView)
    {
        answerText.setText("")
    }

    private fun revealAnswerTwo(answerText: TextView)
    {
        val finAnswer = "The answer is " + answerTwo
        answerText.setText(finAnswer)
    }

    private fun getQuestionTwo(): String
    {
        var question = ""

        val logBase = (2..5).random().toDouble()

        val valueArray = arrayOf(getRandomLogFromValues(1, 3), getRandomLogFromValues(1, 3), getRandomLogFromValues(1, 3))
        val actualValuesArray = arrayOf(logBase.pow(valueArray[0]), logBase.pow(valueArray[1]), logBase.pow(valueArray[2]))

        answerTwo = (valueArray[0] + valueArray[1] + valueArray[2]).toInt()

        question = "2. Log(" + logBase + ")" + actualValuesArray[0] + " + " + "Log(" + logBase + ")" + actualValuesArray[1] + " + " + "Log(" + logBase + ")" + actualValuesArray[2] + " + "

        return question
    }

    private fun getRandomLogFromValues(min: Int, max: Int) : Double
    {
        return (min..max).random().toDouble()
    }
}
