package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.LocaleManager;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Utilities.Utils;

public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    protected View toolbarView;
    protected TextView mToolbarTitle;
    protected abstract int getLayoutResource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleManager.loadLocale(this);
        setContentView(getLayoutResource());

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null){
            setSupportActionBar(toolbar);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    protected Context getContext(){
        return this;
    }

    protected Toolbar getToolbar(){
        return toolbar;
    }

    protected void setToolbarTitle(String value) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(value);
        }
    }
    protected void setToolbarSubtitle(String value) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle(value);
        }
    }

    protected void setHomeAsUpEnabled(boolean up) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(up);
        }
    }

    private void setupToolbar() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
