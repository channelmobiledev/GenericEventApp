package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models.ActivityModel;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Utilities.Utils;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesManager {
    private static final String TAG = "AManager";
    private ActivityModel[] day1;
    private ActivityModel[] day2;
    private Context currentContent;
    private int currentDay;

    private ActivityModel activityToHold;

    private static ActivitiesManager ourInstance = new ActivitiesManager();

    public static ActivitiesManager getInstance() {
        return ourInstance;
    }

    private ActivitiesManager() { }

    public void setCurrentContent(Context content) {
        this.currentContent = content;
    }

    public Context getCurrentContent() {
        return this.currentContent;
    }

    public void setupData() {
        String activitiesDay1 = SPManager.getActivitiesByDay(getCurrentContent(), 1);
        String activitiesDay2 = SPManager.getActivitiesByDay(getCurrentContent(), 2);

        day1 = new Gson().fromJson(activitiesDay1, ActivityModel[].class);
        day2 = new Gson().fromJson(activitiesDay2, ActivityModel[].class);
    }

    public int getActivitiesSelectedDay() {
        String currentDate;

        if (this.currentDay == 0) {
            currentDate = Utils.getCurrentDate();

            if (currentDate.equals("2016-08-20")) {
                this.currentDay = 1;
            } else if (currentDate.equals("2016-08-21")) {
                this.currentDay = 2;
            } else {
                this.currentDay = 1;
            }
        }

        return this.currentDay;
    }

    public void setActivitiesSelectedDay(int currentDay) {
        if (currentDay > 0 && currentDay < 3) {
            this.currentDay = currentDay;
        }
    }

    public ActivityModel[] getActivitiesOfSelectedDay() {
        if (getActivitiesSelectedDay() == 1) {
            return day1;
        } else {
            return day2;
        }
    }

    public ActivityModel[] getNextActivitiesData() {
        ActivityModel listOfActivitiesOfToday[];
        ActivityModel[] resultArray = new ActivityModel[0];
        List<ActivityModel> listOfNextActivities = new ArrayList<>();
        String activityStartTime;
        String activityDoneTime;

        listOfActivitiesOfToday = getActivitiesOfSelectedDay();

        if (Utils.isTodayBeforeEvent()
                && (getActivitiesSelectedDay() == 1 || getActivitiesSelectedDay() == 2)) {
            return listOfActivitiesOfToday;
        } else if (
                (Utils.isTodayDay1OfEvent() && getActivitiesSelectedDay() == 2) ||
                (Utils.isTodayDay2OfEvent() && getActivitiesSelectedDay() == 1)
                ) {
            return resultArray;
        } else {
            for (ActivityModel activity : listOfActivitiesOfToday) {
                activityStartTime = activity.getStart().split(" ")[1];
                activityDoneTime = activity.getStart().split(" ")[1];

                if (!Utils.isActivityCurrent(activityStartTime, activityDoneTime)
                        && !Utils.isActivityDone(activityDoneTime)) {

                    listOfNextActivities.add(activity);
                }
            }
            resultArray = new ActivityModel[ listOfNextActivities.size() ];
            listOfNextActivities.toArray( resultArray );
            return resultArray;
        }
    }

    public ActivityModel[] getCurrentActivitiesData() {
        ActivityModel[] listOfActivitiesOfToday;
        ActivityModel[] resultArray = new ActivityModel[0];
        List<ActivityModel> listOfNextActivities = new ArrayList<>();
        String activityStartTime;
        String activityFinishTime;

        listOfActivitiesOfToday = getActivitiesOfSelectedDay();

        if (Utils.isTodayBeforeEvent()) {
            return resultArray;
        } else if (Utils.isTodayAfterEvent()) {
            return resultArray;
        } else if (
                (this.currentDay == 1 && Utils.isTodayDay1OfEvent()) ||
                (this.currentDay == 2 && Utils.isTodayDay2OfEvent())
              ) {

            for (ActivityModel activity : listOfActivitiesOfToday) {
                activityStartTime = activity.getStart().split(" ")[1];
                activityFinishTime = activity.getEnd().split(" ")[1];

                if (Utils.isActivityCurrent(activityStartTime, activityFinishTime)) {
                    listOfNextActivities.add(activity);
                }
            }

            resultArray = new ActivityModel[ listOfNextActivities.size() ];
            listOfNextActivities.toArray( resultArray );

            return resultArray;
        } else {
            return null;
        }
    }

    public ActivityModel[] getDoneActivitiesData() {
        ActivityModel[] listOfActivitiesOfToday;
        ActivityModel[] resultArray = new ActivityModel[0];
        List<ActivityModel> listOfNextActivities = new ArrayList<>();
        String activityTime;

        listOfActivitiesOfToday = getActivitiesOfSelectedDay();

        if (Utils.isTodayBeforeEvent()) {
            return resultArray;
        } else if (Utils.isTodayDay1OfEvent()
                && getActivitiesSelectedDay() == 2) {
            return resultArray;
        } else if (Utils.isTodayDay2OfEvent()
                && getActivitiesSelectedDay() == 1) {
            return listOfActivitiesOfToday;
        } else {
            for (ActivityModel activity : listOfActivitiesOfToday) {
                activityTime = activity.getEnd().split(" ")[1];

                if (Utils.isActivityDone(activityTime)) {
                    listOfNextActivities.add(activity);
                }
            }

            resultArray = new ActivityModel[ listOfNextActivities.size() ];
            listOfNextActivities.toArray( resultArray );

            return resultArray;
        }
    }

    public void setCurrentActivityHolder(ActivityModel activityToHold) {
        this.activityToHold = activityToHold;
    }

    public ActivityModel getActivityToHold() {
        return activityToHold;
    }

    public ActivityModel[] getRawDataDay1() {
        return day1;
    }

    public ActivityModel[] getRawDataDay2() {
        return day2;
    }
}
