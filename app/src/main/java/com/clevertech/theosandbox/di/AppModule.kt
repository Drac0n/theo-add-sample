package com.clevertech.theosandbox.di

import com.ballyscorp.ballylive.functionality.shared.navigation.AppNavigator
import com.ballyscorp.ballylive.navigation.AppNavigatorImpl
import com.ballyscorp.ballylive.navigation.NavEventProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface AppModule {

    @Singleton
    @Binds
    fun bindNavigationEventProvider(impl: AppNavigatorImpl): NavEventProvider

    @Singleton
    @Binds
    fun bindAppNavigator(impl: AppNavigatorImpl): AppNavigator

}
