package com.example.felipeerazog.moviesapp.activities;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.felipeerazog.moviesapp.R;
import com.example.felipeerazog.moviesapp.adapters.SectionsPagerAdapter;
import com.example.felipeerazog.moviesapp.fragments.PopularFragment;
import com.example.felipeerazog.moviesapp.fragments.TopRatedFragment;
import com.example.felipeerazog.moviesapp.fragments.UpcomingFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity  implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mSectionsPagerAdapter.addFragment(new PopularFragment(), getResources().getString(R.string.tab_text_popular));
        mSectionsPagerAdapter.addFragment(new TopRatedFragment(), getResources().getString(R.string.tab_text_top_rated));
        mSectionsPagerAdapter.addFragment(new UpcomingFragment(), getResources().getString(R.string.tab_text_upcoming));

        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        this.configureDagger();
    }

    private void configureDagger(){
        AndroidInjection.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

}
