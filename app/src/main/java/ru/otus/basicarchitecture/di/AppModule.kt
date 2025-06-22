package ru.otus.basicarchitecture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.otus.basicarchitecture.data.AddressApiService
import ru.otus.basicarchitecture.data.AddressRepositoryImpl
import ru.otus.basicarchitecture.domain.AddressRepository
import ru.otus.basicarchitecture.presentation.WizardCache
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun userInfo() = WizardCache()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://suggestions.dadata.ru/suggestions/api/4_1/rs/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDaDataService(retrofit: Retrofit): AddressApiService {
        return retrofit.create(AddressApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAddressRepository(impl: AddressRepositoryImpl): AddressRepository {
        return impl
    }
}