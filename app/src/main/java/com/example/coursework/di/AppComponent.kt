package com.example.coursework.di

import android.app.Application
import com.example.coursework.details.DetailsActivity
import com.example.coursework.login.LoginActivity
import com.example.coursework.main.BooksActivity
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

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }
}