package me.toptas.rssreader.base;

import android.util.Log;

import me.toptas.rssreader.BuildConfig;

public class Logger {

    private static final String TAG = "Logger";

    public static void logv(String log) {
        if (BuildConfig.IS_TESTING) {
            System.out.print(log);
        } else {
            Log.v(TAG, log);
        }
    }


}