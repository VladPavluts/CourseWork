package com.example.coursework.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coursework.Const.AUTH
import com.example.coursework.R
import com.example.coursework.app.LibraryApplication
import com.example.coursework.databinding.ActivityLoginBinding
import com.example.coursework.main.BooksActivity
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: LoginViewModel
    var isAuth: Boolean=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (application as LibraryApplication).appComponent.inject(this)

        val binding: ActivityLoginBinding =DataBindingUtil.setContentView(this,R.layout.activity_login)

        viewModel=ViewModelProvider(this,viewModelFactory).get(LoginViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.isEmailValid.observe(this, Observer { correct : Boolean->
            if(correct){
                binding.emailLayout.isErrorEnabled = false
            }else{
                binding.emailLayout.error = "Некоректный адрес электронной почты"
            }
        })

        viewModel.isPasswordValid.observe(this, Observer { correct: Boolean ->
            if(correct){
                binding.passwordLayout.isErrorEnabled = false
            } else {
                binding.passwordLayout.error = "Пароль должен быть не меньше 4 символов"
            }
        })

        viewModel.moveToList.observe(this, Observer { event: Boolean ->
            if (event) {
                val intent = Intent(this, BooksActivity::class.java)
                startActivity(intent)
            }
        })
        viewModel.moveToListGuest.observe(this, Observer { event: Boolean ->
            if (event) {
                val intent = Intent(this, BooksActivity::class.java)
                isAuth=false
                intent.putExtra(AUTH,isAuth)
                startActivity(intent)
            }
        })
    }
}

