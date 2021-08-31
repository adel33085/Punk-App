package com.example.punk.base.di

import com.example.punk.base.utils.ConnectivityUtils
import com.example.punk.base.utils.IConnectivityUtils
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun bindConnectivityUtils(connectivityUtils: ConnectivityUtils): IConnectivityUtils
}
