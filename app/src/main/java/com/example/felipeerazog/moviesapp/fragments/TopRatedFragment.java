package com.example.felipeerazog.moviesapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.felipeerazog.moviesapp.R;
import com.example.felipeerazog.moviesapp.activities.DetailActivity;
import com.example.felipeerazog.moviesapp.adapters.MovieListViewAdapter;
import com.example.felipeerazog.moviesapp.database.entities.Movie;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopRatedFragment extends MovieFragment {

    @BindView(R.id.toprated_list)
    ListView moviesList;

    @BindView(R.id.search_toprated)
    SearchView search;

    public TopRatedFragment() {
        CATEGORY = "top_rated";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toprated, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    protected void updateUI(@Nullable List<Movie> movies){
        super.updateUI(movies);
        if (movies != null && !movies.isEmpty() && movieListViewAdapter == null){

            Movie[] array = movies.toArray(new Movie[movies.size()]);

            movieListViewAdapter = new MovieListViewAdapter(this.getContext(), array);
            moviesList.setAdapter(movieListViewAdapter);
            moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra(MOVIE_KEY, (Serializable) array[i]);
                    startActivity(intent);
                }
            });

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    movieListViewAdapter.getFilter().filter(s);
                    return false;
                }
            });
        }
    }
}
