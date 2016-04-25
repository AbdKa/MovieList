package com.ccsit.abdulkader.movielist.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Abdul Kader on 4/23/2016.
 * Used to check the internet Connection
 */
public class ConnectionChecking {
    public static final String NO_CONNECTION = "Please connect to internet";

    /**
     * This method is used to check the connection availability
     * @param context the application context
     * @return true if connection is available otherwise false
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
