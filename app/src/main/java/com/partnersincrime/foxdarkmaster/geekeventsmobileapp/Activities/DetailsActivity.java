package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.ActivitiesManager;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models.ActivityModel;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";
    private Toolbar toolbar;
    private ActivityModel activity;

    protected TextView mTitleView;
    protected TextView mTimeStartView;
    protected TextView mTimeEndView;
    protected TextView mLocationView;
    protected ImageView mImageView;
    protected TextView mDescriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setupToolbar();
        setViews();
        setData();
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setData() {
        String imageUrl;
        activity = ActivitiesManager.getInstance().getActivityToHold();

        mTitleView.setText(activity.getTitle());
        mTimeStartView.setText(activity.getStart().split(" ")[1]);
        mTimeEndView.setText(activity.getEnd().split(" ")[1]);
        mLocationView.setText(activity.getPlace());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            mDescriptionView.setText(Html.fromHtml(activity.getDescr(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            mDescriptionView.setText(Html.fromHtml(activity.getDescr()));
        }

        imageUrl = "https://source.unsplash.com/random/800x600";

//        TODO Review this piece of code
//        if (activity.hasImages()) {
//            imageUrl = Utils.getUrlForImage(activity.getImage());
//        } else {
//            imageUrl = "https://source.unsplash.com/random/800x600";
//        }

        Glide
            .with(this)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(mImageView);
        //mImageView.setImage;
    }

    private void setViews() {
        mTitleView = (TextView) findViewById(R.id.detail_card_title);
        mTimeStartView = (TextView) findViewById(R.id.detail_card_hour_start);
        mTimeEndView = (TextView) findViewById(R.id.detail_card_hour_end);
        mLocationView = (TextView) findViewById(R.id.detail_card_location);
        mImageView = (ImageView) findViewById(R.id.detail_card_image);
        mDescriptionView = (TextView) findViewById(R.id.detail_card_description);

        //        mTitleView.setTypeface(Utils.getTitleFont(this));
        //        mLocationView.setTypeface(Utils.getSubTitleFont(this));
        //        mTimeStartView.setTypeface(Utils.getRegularBoldFont(this));
        //        mTimeEndView.setTypeface(Utils.getRegularBoldFont(this));
        //        mDescriptionView.setTypeface(Utils.getRegularFont(this));
    }
}
