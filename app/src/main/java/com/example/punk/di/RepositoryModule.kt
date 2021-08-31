package com.example.punk.di

import com.example.punk.data.repository.IPunkRepository
import com.example.punk.data.repository.PunkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindPunkRepository(impl: PunkRepository): IPunkRepository
}
