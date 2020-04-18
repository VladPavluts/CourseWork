package com.example.coursework.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coursework.R
import com.example.coursework.app.LibraryApplication
import com.example.coursework.firestore.Firestore
import com.example.coursework.model.Book
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var firestore: Firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as LibraryApplication).appComponent.inject(this)

        firestore.getAllBooks({list:List<Book> -> Log.d("wtf", "$list")},
            {exception: Exception -> Log.d("wtf", "$exception")})
    }
}
