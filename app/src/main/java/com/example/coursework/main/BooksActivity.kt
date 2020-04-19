package com.example.coursework.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.R
import com.example.coursework.app.LibraryApplication
import javax.inject.Inject

class BooksActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: BooksViewModel
    lateinit var adapter: BooksAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        (application as LibraryApplication).appComponent.inject(this)

        val list=findViewById<RecyclerView>(R.id.personList)
        adapter= BooksAdapter(this, mutableListOf()){}

        viewModel=ViewModelProvider(this,viewModelFactory).get(BooksViewModel::class.java)

        viewModel.getFromCloudSt()

        viewModel.books.observe(this, Observer {books ->
            adapter.updateList(books)
        })

        list.adapter=adapter
        list.layoutManager=LinearLayoutManager(this)
    }
}
