package com.example.coursework.review

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.coursework.Const.PATH
import com.example.coursework.R
import com.example.coursework.app.LibraryApplication
import com.example.coursework.details.DetailsActivity
import com.example.coursework.main.BooksViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_add_rev.*
import javax.inject.Inject

class AddRevActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: AddRevViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_rev)


        (application as LibraryApplication).appComponent.inject(this)
        viewModel= ViewModelProvider(this,viewModelFactory).get(AddRevViewModel::class.java)

        val pathID=intent.getStringExtra(PATH)!!

        saveButton.setOnClickListener {
            viewModel.addRev(description = nameEditText.text.toString(),
                rating = nameEditText2.text.toString().toFloat(),path = pathID)
        }
    }
}
