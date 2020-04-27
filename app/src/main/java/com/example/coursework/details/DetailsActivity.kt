package com.example.coursework.details

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.coursework.Const.BOOK
import com.example.coursework.R
import com.example.coursework.app.LibraryApplication
import com.example.coursework.model.Book
import com.example.coursework.storage.Storage
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: DetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        (application as LibraryApplication).appComponent.inject(this)

        viewModel=ViewModelProvider(this,viewModelFactory).get(DetailsViewModel::class.java)

        val book=intent.getParcelableExtra<Book>(BOOK)

        if(book!!.bookPicturePath!="") {
            Storage.getCurrentRef(book.bookPicturePath).downloadUrl.addOnSuccessListener { uri: Uri? ->
                findViewById<ImageView>(R.id.imageView).load(uri){
                    crossfade(true)
                }
            }
        }
        findViewById<TextView>(R.id.bookNameD).text=book.name
        findViewById<TextView>(R.id.descriptionD).text=book.description
        findViewById<RatingBar>(R.id.ratingBarD).rating=book.rating

        findViewById<Button>(R.id.downloadButton).setOnClickListener {
            viewModel.down(this,book)
        }
        viewModel.isButtonVisible.observe(this, Observer {
            if(it == true){
                findViewById<Button>(R.id.openButton).visibility= View.VISIBLE
            }
        })
        findViewById<Button>(R.id.openButton).setOnClickListener { viewModel.open(this,book) }
    }
}
