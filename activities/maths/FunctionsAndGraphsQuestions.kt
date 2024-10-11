package com.example.studified.activities.maths

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import com.example.studified.R

class FunctionsAndGraphsQuestions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_functions_and_graphs_questions)

        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            backNav()
        }

        // Setting up the first question panel
        val questionTextView1: TextView = findViewById(R.id.QuestionText1) as TextView
        val answerText1: TextView = findViewById(R.id.AnswerText1) as TextView
        val regenButton1: Button = findViewById(R.id.RegenerateButton1) as Button
        val revealAnswerButton1: Button = findViewById(R.id.RevealButton1) as Button

        val questionPanel1: QuestionPanel = QuestionPanel(
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

        // Setting up the first question panel
        val questionTextView2: TextView = findViewById(R.id.QuestionText2) as TextView
        val answerText2: TextView = findViewById(R.id.AnswerText2) as TextView
        val regenButton2: Button = findViewById(R.id.RegenerateButton2) as Button
        val revealAnswerButton2: Button = findViewById(R.id.RevealButton2) as Button

        val questionPanel2: QuestionPanel = QuestionPanel(
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
    }



    private fun getQuestionAndAnswer1(questionPanel: QuestionPanel) : String
    {
        questionPanel.answer = ""



        val xValue = (2..9).random()

        var fString = ""
        var gString = ""

        // idefk good luck fixing this one


        // f(g(x))
        val (gXString, gXAnswer) = getRandomFunction(xValue)
        val (fXString, fXAnswer) = getRandomFunction(gXAnswer)
        val fGX = "The answer is " + fXAnswer.toString()

        fString = fXString
        gString = gXString

        questionPanel.answer += fGX


        var question = "1. Given that \n\ng(x) = " + gString + ", \nf(x) = " + fString + ", \nand h(x) = f(g(x)), \n\nFind the value of h(" + xValue.toString() + ")"

        return question
    }

    private fun getRandomFunction(xValue: Int) :Pair<String, Int>
    {
        var randomFunction = ""
        var answer = 0

        val plusAndMinus = arrayOf("+", "-")
        val plusOrMinus = plusAndMinus[(0..1).random()]

        var curMultiplier = getRandomNumberFromRange(2, 5)
        var constantTerm = getRandomNumberFromRange(2, 5)

        randomFunction += curMultiplier.toString() + "x"

        if (plusOrMinus == "+")
        {
            answer = curMultiplier * xValue + constantTerm
        }

        else if(plusOrMinus == "-")
        {
            answer = curMultiplier * xValue - constantTerm
        }

        randomFunction += " " + plusOrMinus + " " + constantTerm.toString()

        return randomFunction to answer
    }

    private fun backNav()
    {
        val options = ActivityOptionsCompat.makeCustomAnimation(this, 0, 0)
        val intent = Intent(this, MathsPage::class.java)
        startActivity(intent, options.toBundle())
    }

    private fun getRandomNumberFromRange(min: Int, max: Int) : Int
    {
        return (min..max).random()
    }

    private fun getQuestionAndAnswer2(questionPanel: QuestionPanel) : String
    {
        var question = "2. Given that f(x) = "

        val xMultiplyValue = getRandomNumberFromRange(2, 4)
        val xValue = getRandomNumberFromRange(-5, 5) * xMultiplyValue

        val (randomFunction, answer) = getRandomFunctionInverse(xValue, xMultiplyValue)

        question += randomFunction

        question += ", find f⁻¹(" + xValue.toString() + ")."

        questionPanel.answer = "The answer is " + answer.toString()

        return question
    }

    private fun getRandomFunctionInverse(xValue: Int, xMultiplyValue: Int): Pair<String, Int>
    {
        var randomFunction = ""
        var answer = 0

        val plusAndMinus = arrayOf("+", "-")
        val plusOrMinus = plusAndMinus[(0..1).random()]

        val cMultiplyValue = getRandomNumberFromRange(2, 8) * xMultiplyValue

        answer += xValue / xMultiplyValue

        if (plusOrMinus == "+")
        {
            answer -= cMultiplyValue
        }
        else if (plusOrMinus == "-")
        {
            answer += cMultiplyValue
        }

        randomFunction += xMultiplyValue.toString() + "x " + plusOrMinus + " " + cMultiplyValue

        return randomFunction to answer
    }
}

public class QuestionPanel(
    val questionText: TextView,
    val answerText: TextView,
    val revealAnswerButton: Button,
    val regenerateQuestionButton: Button,
    var revealed: Boolean,
    var answer: String
)
{
    public fun hideAnswer(questionPanel: QuestionPanel)
    {
        questionPanel.answerText.text = ""
        questionPanel.revealAnswerButton.text = "Answer"
        questionPanel.revealed = false
    }

    public fun revealAnswer(questionPanel: QuestionPanel)
    {
        questionPanel.answerText.text = questionPanel.answer.toString()
        questionPanel.revealAnswerButton.text = "hide"
        questionPanel.revealed = true
    }

    public fun regenerateQuestion(questionPanel: QuestionPanel, question: String)
    {
        questionPanel.hideAnswer(questionPanel)

        questionPanel.questionText.text = question
    }


}
