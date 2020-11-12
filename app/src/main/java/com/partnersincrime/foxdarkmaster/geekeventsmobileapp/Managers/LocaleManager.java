package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers;

import android.content.Context;

public class LocaleManager {
    private static final String TAG = "LOCALE-MANAGER";

    public static void loadLocale(Context context) {
        //setLocale(context, "pt");

        /*
        if (getCurrentLocale(context).startsWith("pt")){
            setLocale(context, "pt");
        } else {
            setLocale(context, "en");
        }
        */
    }

    private static void setLocale(Context context, String locale){
        // Override settings SPManager.setLocale(context, locale);
        //SPManager.setLocale(context, locale);
        //Locale loc = new Locale(locale);
        //SPManager.setLocale(context, "pt");
        //Locale loc = new Locale("pt");
        //Locale.setDefault(loc);
        //Configuration cfg = new Configuration();
        //cfg.locale = loc;
        //context.getResources().updateConfiguration(cfg, null);
    }

    public static String getCurrentLocale(Context context){
        return context
                .getResources()
                .getConfiguration()
                .locale
                .toString();
    }
}
