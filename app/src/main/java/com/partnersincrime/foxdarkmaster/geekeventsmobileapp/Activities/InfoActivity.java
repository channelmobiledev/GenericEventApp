package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

public class InfoActivity extends BaseActivity {

    private Toolbar toolbar;

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

