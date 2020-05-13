package com.example.coursework.di

import android.app.Application
import com.example.coursework.details.DetailsActivity
import com.example.coursework.login.LoginActivity
import com.example.coursework.main.BooksActivity
import com.example.coursework.review.AddRevActivity
import com.example.coursework.review.ReviewActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =
    [FirestoreModule::class,
    FireAuthModule::class,
    ViewModelModule::class])
interface AppComponent {
    fun inject(target: BooksActivity)

    fun inject(target: DetailsActivity)

    fun inject(target: LoginActivity)

    fun inject(target: ReviewActivity)

    fun inject(target: AddRevActivity)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }
}