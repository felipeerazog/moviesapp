package com.example.felipeerazog.moviesapp;


import com.example.felipeerazog.moviesapp.di.module.AppModule;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

public class MovieRepositoryTest {

    AppModule appModule;
    Gson gson;

    @Before
    public void setup(){
        appModule = new AppModule();
        appModule.provideGson();
    }

    @Test
    public void getPopularMoviesTest() {
        // given

        //when

        // then

    }
}
