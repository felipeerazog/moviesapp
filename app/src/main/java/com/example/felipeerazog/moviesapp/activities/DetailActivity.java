package com.example.felipeerazog.moviesapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.felipeerazog.moviesapp.R;
import com.example.felipeerazog.moviesapp.database.entities.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private static final String IMG_FOLDER_URL = "https://image.tmdb.org/t/p/w500/";

    private static final String MOVIE_KEY = "movie";


    @BindView(R.id.detail_title)
    TextView title;

    @BindView(R.id.detail_overview)
    TextView overview;

    @BindView(R.id.release_date)
    TextView releaseDate;

    @BindView(R.id.vote_average)
    RatingBar voteAverage;

    @BindView(R.id.poster)
    ImageView poster;

    @BindView(R.id.vote_average_number)
    TextView voteAverageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Movie movie = (Movie) getIntent().getExtras().getSerializable(MOVIE_KEY);

        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        releaseDate.setText(movie.getReleaseDate());
        voteAverage.setRating((float)movie.getVoteAverage());
        voteAverage.setEnabled(false);
        voteAverageNumber.setText(String.valueOf(movie.getVoteAverage()));
        Glide.with(this).load(IMG_FOLDER_URL + movie.getPoster_path()).into(poster);

    }
}
