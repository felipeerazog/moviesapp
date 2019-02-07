package com.example.felipeerazog.moviesapp.fragments;

import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.felipeerazog.moviesapp.adapters.MovieListViewAdapter;
import com.example.felipeerazog.moviesapp.database.entities.Movie;
import com.example.felipeerazog.moviesapp.viewmodels.MovieViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class MovieFragment extends Fragment{

    public static final String MOVIE_KEY = "movie";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private MovieViewModel viewModel;

    protected String CATEGORY = "popular";

    MovieListViewAdapter movieListViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureViewModel();
    }


    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel(){
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);

        viewModel.init(CATEGORY);
        viewModel.getMovies().observe(this, movies -> updateUI(movies));
    }

    protected void updateUI(@Nullable List<Movie> movies){

    }
}
