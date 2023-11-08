package com.example.bodyworks.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bodyworks.R
import com.example.bodyworks.workoutCategory.WorkoutActivity
import com.example.bodyworks.model.ChildSubWorkoutModel
import com.example.bodyworks.model.ParentWorkoutModel


/**
 * Author: Dhruv Patel
 * Date: October 25, 2023
 */
class ChildSubWorkoutAdapter(private val parentWorkoutList: ParentWorkoutModel,val childSubWorkoutList: MutableList<ChildSubWorkoutModel>):
    RecyclerView.Adapter<ChildSubWorkoutAdapter.ChildViewHolder>() {
   // private var parentActivity: AppCompatActivity? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChildSubWorkoutAdapter.ChildViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.child_subworkout_item_view, parent, false);
        return ChildViewHolder(view);
    }

    class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val childSubWorkoutTitle: TextView
        val carouselViewImage: ImageView
        val subWorkoutCard: CardView

        init {
            childSubWorkoutTitle = itemView.findViewById(R.id.child_subworkout_item_title)
            carouselViewImage = itemView.findViewById(R.id.carousel_image_view)
            subWorkoutCard = itemView.findViewById(R.id.subWorkoutCard)
        }
    }

    override fun onBindViewHolder(holder: ChildSubWorkoutAdapter.ChildViewHolder, position: Int) {
        val childSubWorkoutData = childSubWorkoutList[position];
        holder.childSubWorkoutTitle.text = childSubWorkoutData.workoutName;
        Glide.with(holder.itemView).load(childSubWorkoutData.imgSrc).into(holder.carouselViewImage);
        holder.subWorkoutCard.setOnClickListener {
            openActivityOnCardClick(holder.itemView.context,childSubWorkoutData,parentWorkoutList);
            Log.e("ParentTitle:",parentWorkoutList.categoryTitle.toString());
        }
    }

    override fun getItemCount(): Int {
        return childSubWorkoutList.size;
    }

    private fun openActivityOnCardClick(context: Context, childSubWorkoutData: ChildSubWorkoutModel,parentWorkoutList: ParentWorkoutModel){
        val intent = Intent(context, WorkoutActivity::class.java)
        intent.putExtra("categoryTitle",parentWorkoutList.categoryTitle);
        intent.putExtra("imgSrc", childSubWorkoutData.imgSrc);
        intent.putExtra("workoutName", childSubWorkoutData.workoutName);
        context.startActivity(intent)
    }
}