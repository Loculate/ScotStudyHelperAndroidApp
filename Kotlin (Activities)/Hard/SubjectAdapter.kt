package com.example.studified.datahelpers

import android.content.Intent
import android.widget.Button
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.example.studified.R
import com.example.studified.Subject

class SubjectAdapter(private val context: Context, private val subjects: List<Subject>) : RecyclerView.Adapter<SubjectAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val button: Button = itemView.findViewById(R.id.subjectTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val button = holder.itemView.findViewById<Button>(R.id.subjectTitle)
        val subjectTitle = subjects[position]

        val imgView = holder.itemView.findViewById<ImageView>(R.id.sideImage)
        imgView.setImageBitmap(subjectTitle.image)

        holder.button.apply{
            text = subjectTitle.buttonText
            setBackgroundColor(subjectTitle.backgroundColour)

            button.setOnClickListener {
                val options = ActivityOptionsCompat.makeCustomAnimation(context, 0, 0)
                val intent = Intent(context, subjects[position].destinationActivity)
                context.startActivity(intent, options.toBundle())
            }
        }
    }
}