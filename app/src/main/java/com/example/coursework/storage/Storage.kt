package com.example.coursework.storage

import com.google.firebase.storage.FirebaseStorage

object Storage {

    private val storageInstance=FirebaseStorage.getInstance()
    fun getCurrentRef(path: String) = storageInstance.getReference(path)

}