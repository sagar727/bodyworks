package com.example.bodyworks.dailyWorkoutPlanner.planWorkouts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bodyworks.R

class PlanDailyWorkoutAdapter(val context: Context, private val days: List<EachDayModel>):
    RecyclerView.Adapter<PlanDailyWorkoutAdapter.PlanDailyWorkoutViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanDailyWorkoutViewHolder {
        return PlanDailyWorkoutViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_day_list_view,parent, false))
    }

    override fun getItemCount(): Int {
        return days.size
    }

    override fun onBindViewHolder(holder: PlanDailyWorkoutViewHolder, position: Int) {
        holder.day.text = days[position].day
        holder.rcViewChildItem.setHasFixedSize(true)
        holder.rcViewChildItem.layoutManager = GridLayoutManager(holder.itemView.context, 2)

        //Passing data to child recycler view for exercises
        val adapter = EachDayViewAdapter(context ,days[position].day,days[position].exerciseList)
        holder.rcViewChildItem.adapter = adapter

        //Expandable functionality
        if(days[position].isExpandable) {
            holder.rcViewChildItem.visibility = View.VISIBLE
            holder.imgViewEdit.setImageResource(R.drawable.baseline_close_24)
        }
        else {
            holder.rcViewChildItem.visibility =  View.GONE
            holder.imgViewEdit.setImageResource(R.drawable.baseline_edit_24)
        }

        holder.constraintLayoutDay.setOnClickListener{
            isAnyItemExpanded(position)
            days[position].isExpandable = !days[position].isExpandable
            notifyItemChanged(position)
        }
    }

    private fun isAnyItemExpanded(position: Int){
        val checkOtherOpenViews = days.indexOfFirst {
            it.isExpandable
        }

        if(checkOtherOpenViews >= 0 && checkOtherOpenViews != position){
            days[checkOtherOpenViews].isExpandable = false
            notifyItemChanged(checkOtherOpenViews)
        }
    }

    class PlanDailyWorkoutViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val day: TextView = itemView.findViewById(R.id.day)
        val rcViewChildItem: RecyclerView = itemView.findViewById(R.id.rcViewChildItem)
        val constraintLayoutDay: ConstraintLayout = itemView.findViewById(R.id.constraintLayoutDay)
        val imgViewEdit: ImageView = itemView.findViewById(R.id.imgViewEdit)
    }
}