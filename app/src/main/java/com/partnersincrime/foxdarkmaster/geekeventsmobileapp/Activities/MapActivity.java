package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.widget.ImageView;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

import uk.co.senab.photoview.PhotoViewAttacher;

public class MapActivity extends BaseActivity {
    private ImageView mImageView;
    private PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setActionBar();
        setViews();
        setData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_map;
    }

    private void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.activity_map_title));
    }

    private void setData() {
        Drawable bitmap;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bitmap = getDrawable(R.drawable.current_map);
        } else {
            bitmap = getResources().getDrawable(R.drawable.current_map);
        }

        mImageView.setImageDrawable(bitmap);
        mAttacher = new PhotoViewAttacher(mImageView);
    }

    private void setViews() {
        mImageView = (ImageView) findViewById(R.id.map_image_view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mAttacher.cleanup();
    }
}

