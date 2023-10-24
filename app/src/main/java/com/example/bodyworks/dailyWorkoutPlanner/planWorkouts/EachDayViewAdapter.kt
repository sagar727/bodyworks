package com.example.bodyworks.dailyWorkoutPlanner.planWorkouts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bodyworks.R
import com.google.android.material.card.MaterialCardView

class EachDayViewAdapter(private val exerciseList : List<String>) :
    RecyclerView.Adapter<EachDayViewAdapter.EachDayViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EachDayViewHolder {
        return EachDayViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_day_view, parent, false))
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    override fun onBindViewHolder(holder: EachDayViewHolder, position: Int) {
        holder.txtViewExercise.text= exerciseList[position]
        holder.customChip.setOnClickListener{
            if (holder.selectionIcon.visibility == View.GONE){
                holder.selectionIcon.visibility = View.VISIBLE
                //TODO: Add item to database for exercise
            } else{
                holder.selectionIcon.visibility = View.GONE
                //TODO: Remove item from database for exercise
            }
        }
    }

    class EachDayViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val customChip: MaterialCardView = itemView.findViewById(R.id.customChip)
        val selectionIcon: ImageView = itemView.findViewById(R.id.selectionIcon)
        val txtViewExercise: TextView = itemView.findViewById(R.id.txtViewExercise)
    }
}