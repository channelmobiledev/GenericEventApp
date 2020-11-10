package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Utilities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Handlers.ServerConnection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by foxdarkmaster on 18-07-2016.
 */
public class Utils {
    public static int DAY_ONE_OF_EVENT = 20;
    public static int DAY_TWO_OF_EVENT = 21;
    public static int MONTH_OF_THE_EVENT = 8;
    public static int YEAR_OF_THE_EVENT = 2016;

    private static final String TAG = "Utils";

    public static String getUrlForImage(String image){
        if (image != null && image.contains(".")){
            if (image.contains("thumbnail")) {
                image = image.replace("thumbnail", "slider");
            }

            return ServerConnection.SERVER_URL
                    + image;
        }

        return null;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String getErrorString(Context context, int code) {
        int eId = context.getResources().getIdentifier("error_" + code, "string", context.getPackageName());
        return context.getResources().getString(eId);
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(new Date());
    }

    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();

        return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();

        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);

        return hours + ":" + minutes;
    }

    public static boolean isCurrentTimeBiggerThan(String timeToCompare) {
        String currentTime = Utils.getCurrentTime();
        int hourCurrentTime = Integer.parseInt(currentTime.split(":")[0]);
        int hourCompareTime = Integer.parseInt(timeToCompare.split(":")[0]);
        int minutesCurrentTime = Integer.parseInt(currentTime.split(":")[1]);
        int minutesCompareTime = Integer.parseInt(timeToCompare.split(":")[1]);

        if (hourCurrentTime > hourCompareTime) {
            return true;
        } else if (hourCurrentTime == hourCompareTime) {
            if (minutesCurrentTime > minutesCompareTime) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public static boolean isTodayBeforeEvent() {
        if (Utils.getCurrentYear() == YEAR_OF_THE_EVENT
                && Utils.getCurrentMonth() <= MONTH_OF_THE_EVENT
                && Utils.getCurrentDay() < DAY_ONE_OF_EVENT) {
            return true;
        }

        return false;
    }

    public static boolean isTodayAfterEvent() {
        if (Utils.getCurrentYear() == YEAR_OF_THE_EVENT
                && Utils.getCurrentMonth() >= MONTH_OF_THE_EVENT
                && Utils.getCurrentDay() > DAY_TWO_OF_EVENT) {
            return true;
        }

        return false;
    }

    public static boolean isTodayDay1OfEvent() {
        if (Utils.getCurrentYear() == YEAR_OF_THE_EVENT
                && Utils.getCurrentMonth() == MONTH_OF_THE_EVENT
                && Utils.getCurrentDay() == DAY_ONE_OF_EVENT) {
            return true;
        }

        return false;
    }

    public static boolean isTodayDay2OfEvent() {
        if (Utils.getCurrentYear() == YEAR_OF_THE_EVENT
                && Utils.getCurrentMonth() == MONTH_OF_THE_EVENT
                && Utils.getCurrentDay() == DAY_TWO_OF_EVENT) {
            return true;
        }

        return false;
    }

    public static boolean isActivityCurrent(String StartTimeToCompare, String FinishTimeToCompare) {
        String currentTime = Utils.getCurrentTime();
        int hourCurrentTime = Integer.parseInt(currentTime.split(":")[0]);
        int hourStartCompareTime = Integer.parseInt(StartTimeToCompare.split(":")[0]);
        int hourFinishCompareTime = Integer.parseInt(FinishTimeToCompare.split(":")[0]);

        int minutesCurrentTime = Integer.parseInt(currentTime.split(":")[1]);
        int minutesStartCompareTime = Integer.parseInt(StartTimeToCompare.split(":")[1]);
        int minutesFinishCompareTime = Integer.parseInt(FinishTimeToCompare.split(":")[1]);



        if (hourCurrentTime >= hourStartCompareTime && hourCurrentTime <= hourFinishCompareTime) {
            if (hourCurrentTime == hourStartCompareTime) {
                if (minutesCurrentTime >= minutesStartCompareTime) {
                    return true;
                }
            } else if (hourCurrentTime == hourFinishCompareTime) {
                if (minutesCurrentTime <= minutesFinishCompareTime) {
                    return true;
                }
            } else {
                return true;
            }
        }

        return false;
    }

    public static boolean isActivityDone(String timeToCompare) {
        String currentTime = Utils.getCurrentTime();
        int hourCurrentTime = Integer.parseInt(currentTime.split(":")[0]);
        int minutesCurrentTime = Integer.parseInt(currentTime.split(":")[1]);

        int hourCompareTime = Integer.parseInt(timeToCompare.split(":")[0]);
        int minutesCompareTime = Integer.parseInt(timeToCompare.split(":")[1]);

        if (isTodayBeforeEvent()) {
            return false;
        } else if (isTodayAfterEvent()) {
            return true;
        } else {
            if (hourCurrentTime == hourCompareTime) {
                if (minutesCurrentTime > minutesCompareTime) {
                    return true;
                }
            } else if (hourCurrentTime > hourCompareTime) {
                return true;
            }
        }

        return false;
    }

    public static Typeface getTitleFont(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Azo Sans Uber W01 Regular.otf");
    }

    public static Typeface getSubTitleFont(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Rui Abreu - AzoSans-Medium.otf");
    }

    public static Typeface getRegularFont(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Rui Abreu - AzoSans-Light.otf");
    }

    public static Typeface getRegularBoldFont(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Rui Abreu - AzoSans-Bold.otf");
    }
}
