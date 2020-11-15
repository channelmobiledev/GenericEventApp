package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Adapters.InfoTicketAdapter;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

public class InfoActivity extends BaseActivity {

    private Toolbar toolbar;
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected InfoTicketAdapter mAdapter;

    protected String[] mDataset;

    @Override
    protected int getLayoutResource() {
        return R.layout.information_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_activity);


        setupDemoData();
        setupView();

    }

    private void setupDemoData() {
        mDataset = new String[30];
        for (int i = 0; i < 30; i++) {
            mDataset[i] = "This is element #" + i;
        }
    }

    private void setupView() {
        setActionBar();
        setupRecycler();

    }

    private void setupRecycler() {
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_info_tickets);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new InfoTicketAdapter(mDataset);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.activity_information_title));
    }
}

