package com.example.timestamp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;

public class NetworkStatus {

    public static final int TYPE_WIFI = 1;
    public static final int TYPE_MOBILE = 2;
    public static final int TYPE_NOT_CONNECTED = 3;


    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities nwc = cm.getNetworkCapabilities(cm.getActiveNetwork());

        int result = TYPE_NOT_CONNECTED;
        if (nwc != null) {
            if (nwc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                result = TYPE_WIFI;
            }
            else if (nwc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                result = TYPE_MOBILE;
            }
        }
        return result;
    }

}