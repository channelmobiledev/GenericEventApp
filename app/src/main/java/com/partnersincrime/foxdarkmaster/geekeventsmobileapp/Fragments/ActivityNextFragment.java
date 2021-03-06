package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Adapters.ActivitiesAdapter;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.ActivitiesManager;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models.ActivityModel;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

import java.util.Arrays;

public class ActivityNextFragment extends Fragment {
    private static final String TAG = "ActivityNextFragment";

    View rootView;
    TextView mEmptyView;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    ActivitiesAdapter mAdapter;
    ActivityModel[] currentDay;

    private int mType;
    private String title;

    public Fragment newInstance(int type, String title) {
        Fragment frag = new MainFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setData();
    }

    public void setData() {
        currentDay = ActivitiesManager.getInstance().getNextActivitiesData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_activity, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_activities);
        mEmptyView = (TextView) rootView.findViewById(R.id.empty_view);

        if (currentDay == null || currentDay.length == 0) {
            mRecyclerView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        } else if (currentDay.length == 0 && currentDay[0] == null) {
            Log.d(TAG, "DEBUG Una Banana!");

            mRecyclerView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
            mRecyclerView.setHasFixedSize(true);

            mLayoutManager = new LinearLayoutManager(getActivity());
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(mLayoutManager);

            mAdapter = new ActivitiesAdapter(Arrays.asList(currentDay));
            mRecyclerView.setAdapter(mAdapter);
        }

        return rootView;
    }
}
