package com.example.coursework.login

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class FireAuth(){
    internal val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signIn(email: String, password: String,completionListener:(isSuccessful: Boolean)->Unit) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                completionListener(task.isSuccessful)
            }
    }
}