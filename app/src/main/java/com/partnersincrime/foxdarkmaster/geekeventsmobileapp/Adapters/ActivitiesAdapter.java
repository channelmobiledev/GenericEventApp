package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities.DetailsActivity;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.ActivitiesManager;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models.ActivityModel;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

import java.util.Arrays;
import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ViewHolder> {
    private List<ActivityModel> activitiesList;

    public ActivitiesAdapter(List<ActivityModel> activitiesList) {
        this.activitiesList = activitiesList;
    }


    @Override
    public int getItemCount() {
        return activitiesList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ActivityModel activityData = activitiesList.get(position);
        String imageUrl;

        holder.mTitleView.setText(activityData.getTitle());
        holder.mTimeStartView.setText(activityData.getStart().split(" ")[1]);
        holder.mLocationView.setText(activityData.getPlace());

        imageUrl = "https://source.unsplash.com/random/800x600";

//        TODO Review this piece of code. Refactor with DetailsActivity to remove code duplication
//        if (activity.hasImages()) {
//            imageUrl = Utils.getUrlForImage(activity.getImage());
//        } else {
//            imageUrl = "https://source.unsplash.com/random/800x600";
//        }

//        TODO Refactor the custom font stuff
//        holder.mTitleView.setTypeface(Utils.getTitleFont(holder.mTitleView.getContext()));
//        holder.mTimeStartView.setTypeface(Utils.getRegularBoldFont(holder.mTimeStartView.getContext()));
//        holder.mLocationView.setTypeface(Utils.getRegularFont(holder.mLocationView.getContext()));
//        holder.mReadMoreView.setTypeface(Utils.getRegularBoldFont(holder.mReadMoreView.getContext()));

        Glide
            .with(holder.mImageView.getContext())
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(holder.mImageView);

        holder.mReadMoreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitiesManager.getInstance().setCurrentActivityHolder(activityData);
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                // intent.putExtra("KEY", activitiesList.get(position));
                v.getContext().startActivity(intent);
            }
        });

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitiesManager.getInstance().setCurrentActivityHolder(activityData);
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                // intent.putExtra("KEY", activitiesList.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public ActivitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card, parent, false);

        return new ViewHolder(v);
    }

    public void reloadData(ActivityModel[] currentDay) {
        if (currentDay == null) {
            currentDay = new ActivityModel[0];
        }

        this.activitiesList = Arrays.asList(currentDay);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView mTitleView;
        protected TextView mTimeStartView;
        protected TextView mLocationView;
        protected ImageView mImageView;
        protected TextView mReadMoreView;

        public ViewHolder(View v) {
            super(v);

            mTitleView = (TextView) v.findViewById(R.id.card_title);
            mTimeStartView = (TextView) v.findViewById(R.id.card_hour_start);
            mLocationView = (TextView) v.findViewById(R.id.card_location);
            mImageView = (ImageView) v.findViewById(R.id.card_image);

            mReadMoreView = (TextView) v.findViewById(R.id.card_read_more);
        }
    }
}
