package com.example.bodyworks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bodyworks.R
import com.example.bodyworks.model.ParentWorkoutModel

/**
 * Author: Dhruv Patel
 * Date: October 25, 2023
 */
class ParentWorkoutAdapter(private val parentItemList: MutableList<ParentWorkoutModel>):
    RecyclerView.Adapter<ParentWorkoutAdapter.ParentViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParentWorkoutAdapter.ParentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.parent_workout_category_item_view, parent, false);
        return ParentViewHolder(view);
    }

    class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parentWorkoutTitle: TextView = itemView.findViewById(R.id.parent_workout_item_title);
        val childRecyclerView: RecyclerView = itemView.findViewById(R.id.child_recyclerview);
    }

    override fun onBindViewHolder(holder: ParentWorkoutAdapter.ParentViewHolder, position: Int) {
        val parentWorkoutData = parentItemList[position];
        holder.parentWorkoutTitle.text = parentWorkoutData.categoryTitle;
        val childSubWorkoutAdapter = ChildSubWorkoutAdapter(parentWorkoutData,parentWorkoutData.childSubWorkoutItemList);
        holder.childRecyclerView.adapter = childSubWorkoutAdapter;
    }

    override fun getItemCount(): Int {
        return parentItemList.size;
    }
}