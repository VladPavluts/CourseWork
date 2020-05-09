package com.example.coursework.review

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.R
import com.example.coursework.model.Review

class ReviewAdapter (context : Context, var reviews:MutableList<Review>):
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>(){

    private  val inflater: LayoutInflater= LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            inflater.inflate(R.layout.rewiew_item, parent,false)
        )
    }

    override fun getItemCount(): Int =reviews.size
    private fun getItem(position: Int)= reviews[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        private val userName:TextView=itemView.findViewById(R.id.userName)
        private val rew:TextView=itemView.findViewById(R.id.rew)
        private val ratingBarRew:RatingBar=itemView.findViewById(R.id.ratingBarRew)


        init {
            itemView.setOnClickListener {
            }
        }

        fun bind(review: Review){
            userName.text = review.name
            rew.text = review.description
            ratingBarRew.rating=review.rating
        }
    }

    fun updateList( newList: List<Review>){
        val diffUtilCallback = DiffUtilCallback(this.reviews, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        this.reviews.clear()
        this.reviews.addAll(newList)
        diffResult.dispatchUpdatesTo(this)

    }

    private inner class DiffUtilCallback(
        private val oldList: List<Review>,
        private val newList: List<Review>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = oldList[oldItemPosition]
            val new = newList[newItemPosition]
            return old == new
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = oldList[oldItemPosition]
            val new = newList[newItemPosition]
            return old == new
        }
    }
}