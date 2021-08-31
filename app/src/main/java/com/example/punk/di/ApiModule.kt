package com.example.punk.di

import com.example.punk.data.api.PunkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Provides
    fun providePunkApi(retrofit: Retrofit): PunkApi {
        return retrofit.create(PunkApi::class.java)
    }
}
