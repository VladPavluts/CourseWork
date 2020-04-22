package com.example.coursework.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book (val name:String="",
                 val description: String="",
                 val bookPicturePath:String="",
                 val rating: Float= 0.0F,
                 val filePath: String=""
) : Parcelable