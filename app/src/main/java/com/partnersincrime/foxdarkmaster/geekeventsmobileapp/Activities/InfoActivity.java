package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

/**
 * Created by foxdarkmaster on 01-07-2016.
 */
public class InfoActivity extends BaseActivity {

    Toolbar toolbar;

    @Override
    protected int getLayoutResource() {
        return R.layout.information_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_activity);

        setActionBar();
    }

    private void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.activity_information_title));
    }
}

