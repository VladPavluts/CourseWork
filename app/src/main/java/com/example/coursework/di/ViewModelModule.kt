package com.example.coursework.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coursework.details.DetailsViewModel
import com.example.coursework.login.LoginViewModel
import com.example.coursework.main.BooksViewModel
import com.example.coursework.review.AddRevActivity
import com.example.coursework.review.AddRevViewModel
import com.example.coursework.review.ReviewViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(BooksViewModel::class)
    internal abstract fun booksViewModel(viewModel: BooksViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    internal abstract fun detailsViewModel(viewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReviewViewModel::class)
    internal abstract fun reviewViewModel(viewModel: ReviewViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddRevViewModel::class)
    internal abstract fun addRevViewModel(viewModel: AddRevViewModel): ViewModel


}