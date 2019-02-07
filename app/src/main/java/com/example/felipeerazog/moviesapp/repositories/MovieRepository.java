package com.example.felipeerazog.moviesapp.repositories;

import android.arch.lifecycle.LiveData;
import android.widget.Toast;

import com.example.felipeerazog.moviesapp.App;
import com.example.felipeerazog.moviesapp.api.MovieService;
import com.example.felipeerazog.moviesapp.database.dao.MovieDao;
import com.example.felipeerazog.moviesapp.database.entities.Movie;
import com.example.felipeerazog.moviesapp.database.entities.MovieListInfo;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static final String API_KEY = "10a974073fcb07d013adef1e63f4f65b";
    private static final String SUCCESS_MESSAGE = "Data online";

    private final MovieService movieService;
    private final MovieDao movieDao;
    private final Executor executor;

    @Inject
    public MovieRepository(MovieService movieService, MovieDao movieDao, Executor executor) {
        this.movieService = movieService;
        this.movieDao = movieDao;
        this.executor = executor;
    }

    public LiveData<List<Movie>> getMovies(String category) {
        refreshMovies(category);
        return movieDao.load(category);
    }

    private void refreshMovies(final String category) {
        executor.execute(() -> {
            movieService.loadPopularList(category, API_KEY).enqueue(new Callback<MovieListInfo>() {
                @Override
                public void onResponse(Call<MovieListInfo> call, Response<MovieListInfo> response) {
                    Toast.makeText(App.context, SUCCESS_MESSAGE, Toast.LENGTH_SHORT).show();
                    executor.execute(() -> {
                        MovieListInfo movieListInfo = response.body();
                        for (Movie movie : movieListInfo.getMovies()) {
                            movie.setCategory(category);
                            movieDao.save(movie);
                        }
                    });
                }

                @Override
                public void onFailure(Call<MovieListInfo> call, Throwable t) { }
            });
        });
    }

}
