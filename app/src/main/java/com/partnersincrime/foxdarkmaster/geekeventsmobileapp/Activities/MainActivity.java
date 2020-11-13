package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Handlers.DataTask;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.ActivitiesManager;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.SPManager;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models.ActivityModel;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Utilities.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseActivity implements View.OnClickListener, DataTask.IConnectionListener {

    private static final String TAG = "MainActivity";

    private AsyncTask task;

    private Button buttonActivities;
    private Button buttonMap;
    private Button buttonInformation;

    private TextView mMainTextDays;
    private TextView mMainTextMonth;
    private TextView mMainTextLocation;
    private TextView mMainTextGreeting;

    @Override
    protected int getLayoutResource() {
        return R.layout.main_activity_menu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_menu);

        getOnlineData();
        setupView();
    }

    private void getOnlineData() {
        if (Utils.isNetworkAvailable(this)) {
            task = new DataTask(this, DataTask.ACTIVITIES_GET, this);
            task.execute();
        } else {
            setupData();
        }
    }

    private void setupView() {
        buttonActivities = (Button) findViewById(R.id.buttonActivities);
        buttonMap = (Button) findViewById(R.id.buttonMap);
        buttonInformation = (Button) findViewById(R.id.buttonInfo);
        mMainTextDays = (TextView) findViewById(R.id.main_text_event_days);
        mMainTextMonth = (TextView) findViewById(R.id.main_text_event_month);
        mMainTextLocation = (TextView) findViewById(R.id.main_text_location);
        mMainTextGreeting = (TextView) findViewById(R.id.main_text_greeting);

//        TODO Refactor the custom font stuff
//        buttonActivities.setTypeface(Utils.getRegularFont(this));
//        buttonMap.setTypeface(Utils.getRegularFont(this));
//        buttonInformation.setTypeface(Utils.getRegularFont(this));
//        mMainTextDays.setTypeface(Utils.getTitleFont(this));
//        mMainTextMonth.setTypeface(Utils.getTitleFont(this));
//        mMainTextLocation.setTypeface(Utils.getRegularBoldFont(this));
//        mMainTextGreeting.setTypeface(Utils.getRegularBoldFont(this));

        buttonActivities.setOnClickListener(this);
        buttonMap.setOnClickListener(this);
        buttonInformation.setOnClickListener(this);
    }

    private void setupData() {
        ActivitiesManager.getInstance().setCurrentContent(this);
        ActivitiesManager.getInstance().setupData();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == buttonActivities.getId()) {
            if (checkIfDataIsPresent()) {
                Intent intent = new Intent(this, ActivityContainerActivities.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this,
                        Utils.getErrorString(MainActivity.this, 0),
                        Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == buttonMap.getId()) {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        } else if (v.getId() == buttonInformation.getId()) {
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
        }
    }

    private boolean checkIfDataIsPresent() {
        ActivityModel[] activitiesDay1 = ActivitiesManager.getInstance().getRawDataDay1();
        ActivityModel[] activitiesDay2 = ActivitiesManager.getInstance().getRawDataDay2();
        return activitiesDay1 != null && activitiesDay2 != null;
    }

    @Override
    public void onPre() {
        // TODO Show progress bar
    }

    @Override
    public void onResult(JSONObject result) {
        try {
            String resultDay1 = result.getJSONObject("data")
                    .getJSONArray("2016-08-20")
                    .toString();
            SPManager.setActivitiesByDay(this, 1, resultDay1);

            String resultDay2 = result.getJSONObject("data")
                    .getJSONArray("2016-08-21")
                    .toString();
            SPManager.setActivitiesByDay(this, 2, resultDay2);


            setupData();
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(int code) {
        Log.d(TAG, "DEBUG onError: " + code);
        setupData();
    }
}
