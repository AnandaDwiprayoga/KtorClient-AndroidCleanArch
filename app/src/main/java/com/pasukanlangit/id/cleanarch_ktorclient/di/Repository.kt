package com.pasukanlangit.id.cleanarch_ktorclient.di

import com.pasukanlangit.id.cleanarch_ktorclient.data.datasources.network.ApiService
import com.pasukanlangit.id.cleanarch_ktorclient.data.datasources.sharedpref.SharedPref
import com.pasukanlangit.id.cleanarch_ktorclient.data.repository.MainRepositoryImpl
import com.pasukanlangit.id.cleanarch_ktorclient.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Repository {

    @Provides
    @Singleton
    fun provideMainRepository(apiService: ApiService, sharedPref: SharedPref): MainRepository =
        MainRepositoryImpl(apiService, sharedPref)
}