package com.example.coursework.firestore

import com.example.coursework.model.Book
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.lang.Exception

class Firestore() {
    internal val db=FirebaseFirestore.getInstance()
    private val books=db.collection("books")
    fun getAllBooksByName(successCallback: (list:List<Book>) -> Unit,
                    failureCallback: (e:Exception) -> Unit) {
        books.orderBy("name").get().addOnSuccessListener { querySnapshot ->
            val task=querySnapshot.toObjects(Book::class.java)
            successCallback.invoke(task)
        }
            .addOnFailureListener{exception ->
                failureCallback.invoke(exception)
            }
    }
    fun getAllBooksByRating(successCallback: (list:List<Book>) -> Unit,
                          failureCallback: (e:Exception) -> Unit) {
        books.orderBy("rating",Query.Direction.DESCENDING).get().addOnSuccessListener { querySnapshot ->
            val task=querySnapshot.toObjects(Book::class.java)
            successCallback.invoke(task)
        }
            .addOnFailureListener{exception ->
                failureCallback.invoke(exception)
            }
    }

}