package com.abeltarazona.bbvacryptowallet.di

import com.abeltarazona.bbvacryptowallet.network.ApiClient
import com.abeltarazona.bbvacryptowallet.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by AbelTarazona on 23/10/2021
 */

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideApiInterface(): ApiInterface {
        return ApiClient.getClient()
    }

}