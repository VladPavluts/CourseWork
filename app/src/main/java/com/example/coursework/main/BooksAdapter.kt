package com.example.coursework.main

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.coursework.R
import com.example.coursework.model.Book
import com.example.coursework.storage.Storage
import javax.inject.Inject

class BooksAdapter (val context : Context, var books:MutableList<Book>,
                    private  val clickListener: (position: Int)-> Unit):
    RecyclerView.Adapter<BooksAdapter.ViewHolder>(){

    private  val inflater: LayoutInflater= LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            inflater.inflate(R.layout.book_item, parent,false)
        ) { position ->
            clickListener(position)
        }
    }

    override fun getItemCount(): Int =books.size
    private fun getItem(position: Int)= books[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder (itemView: View,listener: (position: Int) -> Unit): RecyclerView.ViewHolder(itemView){

        private val name:TextView=itemView.findViewById(R.id.bookName)
        private val description:TextView=itemView.findViewById(R.id.description)
        private val ratingBar:RatingBar=itemView.findViewById(R.id.ratingBar)
        private val image:ImageView=itemView.findViewById(R.id.image_id)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener(position)
                }
            }
        }

        fun bind(book: Book){
            name.text = book.name
            description.text = book.description
            ratingBar.rating=book.rating
            if(book.bookPicturePath!="") {
                Storage.getCurrentRef(book.bookPicturePath).downloadUrl.addOnSuccessListener {uri: Uri? ->
                    image.load(uri){
                        crossfade(true)
                    }
                }
            }
            else {
                image.load(R.mipmap.ic_book){crossfade(true)}
            }
        }
    }

    fun updateList( newList: List<Book>){
        val diffUtilCallback = DiffUtilCallback(this.books, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        this.books.clear()
        this.books.addAll(newList)
        diffResult.dispatchUpdatesTo(this)

    }

    private inner class DiffUtilCallback(
        private val oldList: List<Book>,
        private val newList: List<Book>
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