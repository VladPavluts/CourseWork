package com.example.coursework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coursework.firestore.Firestore
import com.example.coursework.model.Book

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firestore=Firestore()

        firestore.getAllBooks({list:List<Book> -> Log.d("wtf", "$list")},
            {exception: Exception -> Log.d("wtf", "$exception")})
    }
}
