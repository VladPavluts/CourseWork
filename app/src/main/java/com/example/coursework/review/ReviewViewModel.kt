package com.example.coursework.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coursework.firestore.Firestore
import com.example.coursework.model.Book
import com.example.coursework.model.Review
import javax.inject.Inject

class ReviewViewModel @Inject constructor(private val db: Firestore):ViewModel(){

    private val reviewsLiveData: MutableLiveData<List<Review>> = MutableLiveData()
    val reviews: LiveData<List<Review>>
        get() = reviewsLiveData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getFromCloudStReview(path:String){
        db.getAllReviews({list: List<Review> ->
            reviewsLiveData.value=list
            _isLoading.value=false},{},path)

        _isLoading.value=true
    }

}