package com.example.felipeerazog.moviesapp.di.module;

import com.example.felipeerazog.moviesapp.fragments.PopularFragment;
import com.example.felipeerazog.moviesapp.fragments.TopRatedFragment;
import com.example.felipeerazog.moviesapp.fragments.UpcomingFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract PopularFragment contributePopularFragment();

    @ContributesAndroidInjector
    abstract TopRatedFragment contributeTopRatedFragment();

    @ContributesAndroidInjector
    abstract UpcomingFragment contributeUpcomingFragment();
}
