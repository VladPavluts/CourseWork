package com.example.coursework.di

import android.app.Application
import com.example.coursework.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =
    [FirestoreModule::class,
    StorageModule::class,
    ViewModelModule::class])
interface AppComponent {
    fun inject(target: MainActivity)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }
}