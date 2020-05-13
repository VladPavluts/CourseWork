package com.example.coursework.review

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.Const.PATH
import com.example.coursework.R
import com.example.coursework.app.LibraryApplication
import kotlinx.android.synthetic.main.activity_review.*
import javax.inject.Inject

class ReviewActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: ReviewViewModel
    lateinit var adapter: ReviewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        (application as LibraryApplication).appComponent.inject(this)

        val bID=intent.getStringExtra(PATH)!!
        val list=findViewById<RecyclerView>(R.id.revList)

        adapter= ReviewAdapter(this, mutableListOf())

        viewModel=ViewModelProvider(this,viewModelFactory).get(ReviewViewModel::class.java)

        viewModel.getFromCloudStReview(bID)

        viewModel.reviews.observe(this, Observer {reviews ->
            adapter.updateList(reviews)
        })

        viewModel.isLoading.observe(this, Observer {
            if(it == true){
                startLoading()
            }else{
                stopLoading()
            }
        })


        fabPlus.setColorFilter(Color.WHITE)
        fabPlus.setOnClickListener {
            val intent = Intent(this, AddRevActivity::class.java)
            intent.putExtra(PATH,bID)
            startActivity(intent)
        }
        list.adapter=adapter
        list.layoutManager= LinearLayoutManager(this)

    }
    private fun startLoading(){
        constraintLayout.visibility = View.INVISIBLE
        progressBarRew.visibility = View.VISIBLE
    }

    private fun stopLoading(){
        constraintLayout.visibility = View.VISIBLE
        progressBarRew.visibility = View.INVISIBLE
    }
}
