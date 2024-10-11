package com.example.studified.datahelpers
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import com.example.studified.NotesAndQuestions
import com.example.studified.R
import android.view.LayoutInflater
import androidx.core.app.ActivityOptionsCompat

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val topicImage: ImageView = view.findViewById(R.id.topicImage)
    val notes: Button = view.findViewById(R.id.Notes)
    val questions: Button = view.findViewById(R.id.Questions)
}

class NotesAndQuestionsAdapter (private val notesAndQuestionsArray: Array<NotesAndQuestions>): RecyclerView.Adapter<MyViewHolder>()
{

    companion object {
        const val MIDDLE = Int.MAX_VALUE / 2

        fun getStartPosition(arraySize: Int): Int {
            return MIDDLE - MIDDLE % arraySize // ensure it's a multiple of list size
        }
    }

    private fun getRealPosition(position: Int): Int
    {
        return position % notesAndQuestionsArray.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject_rec_cards, parent, false)
        return MyViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val index = getRealPosition(position)

        holder.topicImage.setImageBitmap(notesAndQuestionsArray[index].image)
        holder.notes.text = notesAndQuestionsArray[index].notesText
        holder.questions.text = notesAndQuestionsArray[index].questionsText

        holder.notes.setOnClickListener {
            val options = ActivityOptionsCompat.makeCustomAnimation(it.context, 0, 0)
            val intent = Intent(it.context, notesAndQuestionsArray[index].notesDestinationActivity)
            it.context.startActivity(intent, options.toBundle())
        }

        holder.questions.setOnClickListener {
            val options = ActivityOptionsCompat.makeCustomAnimation(it.context, 0, 0)
            val intent = Intent(it.context, notesAndQuestionsArray[index].questionsDestinationActivity)
            it.context.startActivity(intent, options.toBundle())
        }
    }

    // override fun getItemCount() = notesAndQuestionsArray.size
    override fun getItemCount() = Int.MAX_VALUE


}