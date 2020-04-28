package com.example.coursework.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.Const.BOOK
import com.example.coursework.R
import com.example.coursework.app.LibraryApplication
import com.example.coursework.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_books.*
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

        viewModel.isLoading.observe(this, Observer {
            if(it == true){
                startLoading()
            }else{
                stopLoading()
            }
        })

        list.adapter=adapter
        list.layoutManager=LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.search_menu,menu)
        viewModel.books.observe(this, Observer{ books ->
            val search=menu.findItem(R.id.itemSearch).actionView as SearchView
            search.maxWidth= Int.MAX_VALUE
            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    query?.let {
                        adapter.updateList(books.filter {
                            it.name.contains(query, ignoreCase = true)
                          })
                    }
                    return true
                }

            })
            search.setOnCloseListener {
                adapter.updateList(books)
                return@setOnCloseListener false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun startLoading(){
        constraintLayout.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading(){
        constraintLayout.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }
}
