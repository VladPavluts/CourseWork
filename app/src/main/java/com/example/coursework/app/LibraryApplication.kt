package com.example.coursework.app

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import com.example.coursework.di.AppComponent
import com.example.coursework.di.DaggerAppComponent


class LibraryApplication : Application(){
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent=initAppComponent(this)
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }

    private fun initAppComponent(app: Application): AppComponent{
        return DaggerAppComponent.builder()
            .application(app)
            .build()
    }
}