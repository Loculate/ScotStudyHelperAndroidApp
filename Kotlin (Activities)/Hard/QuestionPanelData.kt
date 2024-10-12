package com.example.studified.datahelpers
import android.widget.TextView
import android.widget.Button

public class QuestionPanelData(
    val questionText: TextView,
    val answerText: TextView,
    val revealAnswerButton: Button,
    val regenerateQuestionButton: Button,
    var revealed: Boolean,
    var answer: Double
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