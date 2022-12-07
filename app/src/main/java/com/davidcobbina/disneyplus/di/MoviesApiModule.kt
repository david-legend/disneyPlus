package com.davidcobbina.disneyplus.di

import com.davidcobbina.disneyplus.data.repositories.MoviesRepository
import com.davidcobbina.disneyplus.data.remote.api.ApiConstants
import com.davidcobbina.disneyplus.data.remote.api.MoviesAPi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesApiModule {

    @Provides
    @Singleton
    fun providesApi(builder: Retrofit.Builder): MoviesAPi {
        return builder.build().create(MoviesAPi::class.java)
    }

    @Provides
    @Singleton
    fun providesMovieRepository(moviesAPi: MoviesAPi): MoviesRepository = MoviesRepository(moviesAPi)

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
}