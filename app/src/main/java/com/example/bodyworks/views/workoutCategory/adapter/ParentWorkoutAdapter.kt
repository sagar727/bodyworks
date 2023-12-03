package com.example.bodyworks.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bodyworks.R
import com.example.bodyworks.model.ParentWorkoutModel

/**
 * Author: Dhruv Patel
 * Date: October 25, 2023
 */
class ParentWorkoutAdapter(val context: Context, val parentItemList: MutableList<ParentWorkoutModel>):
    RecyclerView.Adapter<ParentWorkoutAdapter.ParentViewHolder>() {

    private lateinit var sharedPreferences: SharedPreferences
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
        sharedPreferences = this.let { PreferenceManager.getDefaultSharedPreferences(context) }!!
        val themeColor = sharedPreferences.getString("Current Theme", context.getString(R.string.red))
        val color =  changeTheme(themeColor)
        holder.parentWorkoutTitle.setTextColor(context.getColor(color))
        val parentWorkoutData = parentItemList[position];
        holder.parentWorkoutTitle.text = parentWorkoutData.categoryTitle;
        val childSubWorkoutAdapter = ChildSubWorkoutAdapter(parentWorkoutData,parentWorkoutData.childSubWorkoutItemList);
        holder.childRecyclerView.adapter = childSubWorkoutAdapter;
    }

    override fun getItemCount(): Int {
        return parentItemList.size;
    }

    private fun changeTheme(themeColor: String?): Int {
        when (themeColor) {
            "Red" -> {
                return R.color.primary
            }
            "Orange" -> {
                return R.color.orange
            }
            "Brown" -> {
                return R.color.brown
            }
        }
        return 0
    }
}