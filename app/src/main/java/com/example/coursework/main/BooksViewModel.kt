package com.example.coursework.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coursework.firestore.Firestore
import com.example.coursework.model.Book
import javax.inject.Inject

class BooksViewModel @Inject constructor(private val db: Firestore):ViewModel(){

    private val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val books: LiveData<List<Book>>
        get() = booksLiveData

    fun getFromCloudSt(){
        db.getAllBooks({list: List<Book> -> booksLiveData.value=list  },{})
    }
}