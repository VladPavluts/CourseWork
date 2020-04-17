package com.example.coursework.firestore

import com.example.coursework.model.Book
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class Firestore() {
    internal val db=FirebaseFirestore.getInstance()
    private val books=db.collection("books")
    fun getAllBooks(successCallback: (list:List<Book>) -> Unit,
                    failureCallback: (e:Exception) -> Unit) {
        books.get().addOnSuccessListener { querySnapshot ->
            val task=querySnapshot.toObjects(Book::class.java)
            successCallback.invoke(task)
        }
            .addOnFailureListener{exception ->
                failureCallback.invoke(exception)
            }



    }

}