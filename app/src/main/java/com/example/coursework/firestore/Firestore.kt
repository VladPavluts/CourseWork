package com.example.coursework.firestore

import com.example.coursework.model.Book
import com.example.coursework.model.Review
import com.google.firebase.firestore.DocumentReference
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

    fun getAllReviews(successCallback: (list:List<Review>) -> Unit,
                      failureCallback: (e:Exception) -> Unit, path: String){
        val reviews = books.document(path).collection("reviews")

        reviews.get().addOnSuccessListener { querySnapshot ->
            val task=querySnapshot.toObjects(Review::class.java)
            successCallback.invoke(task)
        }
            .addOnFailureListener{exception ->
                failureCallback.invoke(exception)
            }
    }

    fun createCurrentRev(name:String,description:String,rating: Float,path:String){
        val cRef=books.document(path).collection("reviews").document()
        cRef.get().addOnSuccessListener{documentSnapshot ->
            if(!documentSnapshot.exists()){
                val rev=Review(name, description, rating)
                cRef.set(rev)
            }
        }
    }


}