package com.example.felipeerazog.moviesapp.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.felipeerazog.moviesapp.api.MovieService;
import com.example.felipeerazog.moviesapp.database.MovieDatabase;
import com.example.felipeerazog.moviesapp.database.dao.MovieDao;
import com.example.felipeerazog.moviesapp.repositories.MovieRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    private static String BASE_URL = "https://api.themoviedb.org/3/";

    @Provides
    @Singleton
    public MovieDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                MovieDatabase.class, "MyDatabase.db")
                .build();
    }

    @Provides
    @Singleton
    public MovieDao provideMovieDao(MovieDatabase database) {
        return database.movieDao();
    }

    @Provides
    public Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    public MovieRepository provideMovieRepository(MovieService movieService, MovieDao movieDao, Executor executor) {
        return new MovieRepository(movieService, movieDao, executor);
    }

    @Provides
    public Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    public Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public MovieService provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(MovieService.class);
    }
}

