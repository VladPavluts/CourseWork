package com.example.coursework.di

import com.example.coursework.firestore.Firestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirestoreModule {
    @Provides
    @Singleton
    fun provideFirestore()=Firestore()
}