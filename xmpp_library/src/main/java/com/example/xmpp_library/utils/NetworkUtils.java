package com.example.xmpp_library.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;


public class NetworkUtils {
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager conn = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    private static RxTxBytes getRxTxBytesSinceBoot(Context context) {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {}

        if (applicationInfo != null) {
            int uid = applicationInfo.uid;
            long rxBytes = TrafficStats.getUidRxBytes(uid);
            long txBytes = TrafficStats.getUidTxBytes(uid);
            return new RxTxBytes(rxBytes, txBytes);
        } else {
            return null;
        }
    }


    public static class RxTxBytes {
        public long rxBytes;
        public long txBytes;

        public RxTxBytes(long rxBytes, long txBytes) {
            this.rxBytes = rxBytes;
            this.txBytes = txBytes;
        }
    }
}