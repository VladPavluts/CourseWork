package com.example.coursework.review

import androidx.lifecycle.ViewModel
import com.example.coursework.firestore.Firestore
import com.example.coursework.login.FireAuth
import javax.inject.Inject

class AddRevViewModel  @Inject constructor(private val db: Firestore, private val auth: FireAuth) :ViewModel(){
    fun addRev(description:String,rating:Float,path:String){
        db.createCurrentRev(auth.mAuth.currentUser?.email!!,description,rating,path)
    }
}