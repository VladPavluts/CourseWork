package com.example.coursework.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val auth: FireAuth):ViewModel(){
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val isEmailValid = MutableLiveData<Boolean>()
    val isPasswordValid = MutableLiveData<Boolean>()
    private val _errorCode = MutableLiveData<Int>()

    fun signIn(){
        checkEmail()
        checkPassword()
        if(isEmailValid.value == true && isPasswordValid.value == true){
            auth.signIn(email.value ?: "", password.value ?: "") { value -> finishedEvent(value)}
            moveToList()
        } else {
            _errorCode.value = 2 //wrong fields
        }
    }

    private val _moveToList = MutableLiveData<Boolean>()
    val moveToList: LiveData<Boolean>
        get() = _moveToList

    private fun moveToList(){
        _moveToList.value = true
    }

    private val _moveToListGuest = MutableLiveData<Boolean>()
    val moveToListGuest: LiveData<Boolean>
        get() = _moveToListGuest

    fun moveToListGuest(){
        _moveToListGuest.value = true
    }

    private fun finishedEvent(result: Boolean){
        if(result){
            _errorCode.value = 0//everything ok
        } else {
            _errorCode.value = 1//such user does not exist
        }
    }

    fun checkEmail(){
        isEmailValid.value = email.value?.contains("@")
    }

    fun checkPassword(){
        isPasswordValid.value = password.value?.length ?: 0 >= 4
    }

}