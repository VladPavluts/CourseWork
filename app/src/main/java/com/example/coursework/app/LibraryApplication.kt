package com.example.coursework.app

import android.app.Application
import com.example.coursework.di.AppComponent
import com.example.coursework.di.DaggerAppComponent

class LibraryApplication : Application(){
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent=initAppComponent(this)
    }

    private fun initAppComponent(app: Application): AppComponent{
        return DaggerAppComponent.builder()
            .application(app)
            .build()
    }
}