package com.example.felipeerazog.moviesapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.felipeerazog.moviesapp.database.dao.MovieDao;
import com.example.felipeerazog.moviesapp.database.entities.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    private static volatile MovieDatabase INSTANCE;

    public abstract MovieDao movieDao();

}