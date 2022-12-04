package com.davidcobbina.disneyplus.di

import com.davidcobbina.disneyplus.data.MoviesRepository
import com.davidcobbina.disneyplus.data.api.ApiConstants
import com.davidcobbina.disneyplus.data.api.MoviesAPi
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