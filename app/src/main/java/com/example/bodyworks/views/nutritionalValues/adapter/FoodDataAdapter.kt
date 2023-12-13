package com.example.bodyworks.views.nutritionalValues.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.bodyworks.R
import com.example.bodyworks.views.nutritionalValues.model.FoodDataModel

class FoodDataAdapter(private val foodList: MutableList<FoodDataModel>) :
    RecyclerView.Adapter<FoodDataAdapter.FoodDataViewHolder>() {


    class FoodDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleTv);
        val picture: ImageView = itemView.findViewById(R.id.logoIv);
        val description: TextView = itemView.findViewById((R.id.nutritionalValuesData));
        val layoutID: ConstraintLayout = itemView.findViewById((R.id.constraintLayout));

        fun collapseExpandedView() {
            description.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodDataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_item_view, parent, false);
        return FoodDataViewHolder(view);
    }

    override fun getItemCount(): Int {
        return foodList.size;
    }

    override fun onBindViewHolder(holder: FoodDataViewHolder, position: Int) {
        val foodItemData = foodList[position];
        holder.title.text = foodItemData.title;
        holder.description.text = foodItemData.description;
        holder.picture.setImageResource(foodItemData.pictureURL);

        val isExpandable: Boolean = foodItemData.isExpandable
        holder.description.visibility = if (isExpandable) View.VISIBLE else View.GONE;

        holder.layoutID.setOnClickListener {
            isAnyItemExpanded(position)
            foodItemData.isExpandable = !foodItemData.isExpandable;
            notifyItemChanged(position, Unit)
        }
    }

    private fun isAnyItemExpanded(position: Int) {
        val temp = foodList.indexOfFirst {
            it.isExpandable
        }
        if (temp >= 0 && temp != position) {
            foodList[temp].isExpandable = false
            notifyItemChanged(temp, 0)
        }
    }

    fun updateData(newDataList: MutableList<FoodDataModel>) {
        foodList.clear()
        foodList.addAll(newDataList)
        notifyDataSetChanged()
    }

}