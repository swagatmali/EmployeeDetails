package com.example.employeedetails.di

import android.content.Context
import com.example.employeedetails.api.EmployeeAPI
import com.example.employeedetails.util.AppConstant
import com.example.employeedetails.util.DefaultDispatcherProvider
import com.example.employeedetails.util.DispatcherProvider
import com.example.employeedetails.util.NetworkHelper
import com.example.employeedetails.util.NetworkHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(
        @BaseUrl baseUrl: String,
        ): Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesEmployeeAPI(retrofit: Retrofit) : EmployeeAPI {
        return retrofit.create(EmployeeAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelperImpl(context)
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = AppConstant.BASE_URL

    @Provides
    @Singleton
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()

}