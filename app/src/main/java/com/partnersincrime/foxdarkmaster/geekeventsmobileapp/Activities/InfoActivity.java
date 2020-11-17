package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Adapters.InfoTicketAdapter;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Interfaces.InfoTicketClickListener;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models.TicketModel;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

import java.util.ArrayList;

public class InfoActivity extends BaseActivity implements InfoTicketClickListener {

    private Toolbar toolbar;
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected InfoTicketAdapter mAdapter;

    protected ArrayList<TicketModel> mDataset;

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
        mDataset = new ArrayList<TicketModel>() {{
          add(new TicketModel("One day ticket", "Access day 1 event", 5.00, "EUR", "http://www.buy-dummy-ticket-1.com"));
          add(new TicketModel("Two day ticket", "Access day 2 event", 8.00, "EUR", "http://www.buy-dummy-ticket-2.com"));
          add(new TicketModel("Digital Deluxe Ticket", "Access the whole event", 9.99, "EUR", "http://www.buy-dummy-ticket-3.com"));
        }};
    }

    private void setupView() {
        setActionBar();
        setupRecycler();

    }

    private void setupRecycler() {
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_info_tickets);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new InfoTicketAdapter(mDataset, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.activity_information_title));
    }

    @Override
    public void onItemClick(String addressToBuyTicket) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(addressToBuyTicket));
        startActivity(browserIntent);
    }
}

