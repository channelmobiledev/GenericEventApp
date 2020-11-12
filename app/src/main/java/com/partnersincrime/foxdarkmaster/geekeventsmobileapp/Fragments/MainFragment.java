package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Adapters.ActivitiesAdapter;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.ActivitiesManager;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models.ActivityModel;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

import java.util.Arrays;

public class MainFragment extends Fragment {
    public static final int TYPE_NEXT = 0;
    public static final int TYPE_CURRENT = 1;
    public static final int TYPE_DONE = 2;
    private static final String KEY_TYPE = "TypeKey";

    private static final String KEY_TITLE = "TitleKey";

    private static final String TAG = "ActivityNextFragment";

    View rootView;
    TextView mEmptyView;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    ActivitiesAdapter mAdapter;
    ActivityModel[] currentDay;

    private int mType;
    private String title;


    public static MainFragment newInstance(int type, String title) {
        MainFragment frag = new MainFragment();

        Bundle args = new Bundle();
        args.putInt(KEY_TYPE, type);
        args.putString(KEY_TITLE, title);

        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        title = getArguments().getString(KEY_TITLE);
        mType = getArguments().getInt(KEY_TYPE);

        setData();
    }

    public void setData() {
        switch (mType) {
            case TYPE_NEXT:
                currentDay = ActivitiesManager.getInstance().getNextActivitiesData();
                break;
            case TYPE_CURRENT:
                currentDay = ActivitiesManager.getInstance().getCurrentActivitiesData();
                break;
            case TYPE_DONE:
                currentDay = ActivitiesManager.getInstance().getDoneActivitiesData();
                break;
        }

        if (mAdapter != null) {
            mAdapter.reloadData(currentDay);
            mAdapter.notifyDataSetChanged();
        }
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

    public String getTitle() {
        if (title == null) {
            title = getArguments().getString(KEY_TITLE);
        }

        return title;
    }
}
