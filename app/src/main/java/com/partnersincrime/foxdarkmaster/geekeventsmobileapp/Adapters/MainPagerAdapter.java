package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Adapters;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Fragments.MainFragment;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    private List<MainFragment> mFragmentList;
    Context context;

    private static final String TAG = "MainPagerAdapter";


    public MainPagerAdapter(FragmentManager manager, Context context, ArrayList<MainFragment> objs) {
        super(manager);
        this.context = context;
        this.mFragmentList = objs;

        for (MainFragment fragment : mFragmentList) {
            fragment.setData();
        }
    }

    @Override
    public int getCount() { return mFragmentList.size(); }

    @Override
    public Fragment getItem(int position) { return mFragmentList.get(position); }

    public void addFragment(MainFragment fragment) {
        mFragmentList.add(fragment);
    }

    public void setNewData() {
        for (MainFragment fragment : mFragmentList) {
            fragment.setData();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) { return mFragmentList.get(position).getTitle(); }
}