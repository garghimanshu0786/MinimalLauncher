package com.example.ciscolauncher.di

import android.content.Context
import com.example.ciscolauncher.localstorage.ILocalStorage
import com.example.ciscolauncher.localstorage.PreferenceDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Singleton
	@Provides
	fun provideDataStoreRepository(
		@ApplicationContext app: Context
	): ILocalStorage = PreferenceDataStore(app)

}