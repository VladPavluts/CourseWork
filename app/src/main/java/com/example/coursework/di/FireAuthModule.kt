package com.example.coursework.di

import com.example.coursework.login.FireAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FireAuthModule {
    @Provides
    @Singleton
    fun provideFireAuth()=FireAuth()
}