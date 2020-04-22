package com.example.coursework.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.Const.BOOK
import com.example.coursework.R
import com.example.coursework.app.LibraryApplication
import com.example.coursework.details.DetailsActivity
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
        adapter= BooksAdapter(this, mutableListOf()){position: Int ->
            val book= viewModel.books.value!![position]
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(BOOK,book)
            startActivity(intent)
        }

        viewModel=ViewModelProvider(this,viewModelFactory).get(BooksViewModel::class.java)

        viewModel.getFromCloudSt()

        viewModel.books.observe(this, Observer {books ->
            adapter.updateList(books)
        })

        list.adapter=adapter
        list.layoutManager=LinearLayoutManager(this)
    }
}
